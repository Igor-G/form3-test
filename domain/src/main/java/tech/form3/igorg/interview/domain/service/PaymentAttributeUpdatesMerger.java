package tech.form3.igorg.interview.domain.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import tech.form3.igorg.interview.model.payment.PaymentAttributes;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;

/**
 * Payment attributes updates merger.
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentAttributeUpdatesMerger {

    private final ObjectMapper objectMapper;

    /**
     * Merges the attribute updates to the persisted data.
     *
     * @param originalPaymentAttributes    the original (persisted) payment data.
     * @param updates            the map of updates
     */
    public PaymentAttributes mergeAttributeUpdates(PaymentAttributes originalPaymentAttributes,
                                                   Map<String, Object> updates) {
        PaymentAttributes attributes = originalPaymentAttributes == null
                ? new PaymentAttributes() : originalPaymentAttributes;
        PaymentAttributes paymentAttributes = objectMapper.convertValue(updates, PaymentAttributes.class);
        copyValuesFromSourceToNullTarget(paymentAttributes, attributes);
        copyUpdates(paymentAttributes, attributes,
                convertNames(updates, PaymentAttributes.class), emptyList());
        return attributes;
    }

    private void copyValuesFromSourceToNullTarget(Object source, Object target) {
        if (source.getClass() != target.getClass()) {
            throw new IllegalStateException(
                    "Cannot assign values from class '" + source.getClass() + "' to class '" + target.getClass() + "'");
        }
        ReflectionUtils.doWithFields(source.getClass(), f -> {
            Object sourceValue = getProperty(source, f.getName());
            Object targetValue = getProperty(target, f.getName());
            if (sourceValue != null && targetValue == null) {
                setProperty(target, f.getName(), sourceValue);
            } else if (sourceValue != null && !BeanUtils.isSimpleProperty(sourceValue.getClass())) {
                copyValuesFromSourceToNullTarget(sourceValue, targetValue);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void copyUpdates(PaymentAttributes source, PaymentAttributes target,
                             Map<String, Object> updates, List<String> path) {
        updates.forEach((key, value) -> {
            List<String> newPath = new ArrayList<>(path);
            newPath.add(key);
            if (value != null && Map.class.isAssignableFrom(value.getClass())) {
                copyUpdates(source, target, (Map<String, Object>) value, newPath);
            } else {
                String pathString = newPath.stream().collect(Collectors.joining("."));
                try {
                    Object nestedProperty = PropertyUtils.getNestedProperty(source, pathString);
                    PropertyUtils.setNestedProperty(target, pathString, nestedProperty);
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> convertNames(Map<String, Object> attributes, Class clazz) {
        Map<String, Object> currentMap = new HashMap<>();

        attributes.forEach((key, value) -> {
            Field field = findFieldWithJsonAnnotation(clazz, key);
            if (value != null && Map.class.isAssignableFrom(value.getClass())) {
                currentMap.put(field.getName(), convertNames((Map<String, Object>) value, field.getType()));
            } else {
                currentMap.put(field.getName(), value);
            }
        });

        return currentMap;
    }

    private Object getProperty(Object source, String propertyName) {
        try {
            return PropertyUtils.getSimpleProperty(source, propertyName);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private void setProperty(Object target, String propertyName, Object value) {
        try {
            PropertyUtils.setProperty(target, propertyName, value);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private Field findFieldWithJsonAnnotation(Class clazz, String jsonAnnotationValue) {
        return Stream.of(clazz.getDeclaredFields())
                .filter(f -> Stream.of(f.getAnnotationsByType(JsonProperty.class)).anyMatch(a -> a.value().equals(jsonAnnotationValue)))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Cannot find field for jsonProperty '"
                        + jsonAnnotationValue + "' in class + '" + clazz.getSimpleName()));
    }


}
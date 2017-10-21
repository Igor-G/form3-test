package tech.form3.igorg.interview.infrastructure.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.form3.igorg.interview.model.serialization.SerializationContainer;

import java.io.IOException;
import java.util.Map;

/**
 * Serialization container hibernate listener (executes serialization and deserialization).
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SerializationContainerListener
        implements PostLoadEventListener, PersistEventListener, MergeEventListener {

    private final ObjectMapper objectMapper;

    @Override
    public void onPostLoad(PostLoadEvent event) {
        Object entity = event.getEntity();
        if (SerializationContainer.class.isAssignableFrom(entity.getClass())) {
            try {
                ((SerializationContainer) entity).deserialize(objectMapper);
            } catch (IOException e) {
                // TODO custom exception should be created
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onMerge(MergeEvent event) throws HibernateException {
        serialize(event.getEntity());
    }

    @Override
    public void onMerge(MergeEvent event, Map copiedAlready) throws HibernateException {
        serialize(event.getEntity());
    }

    @Override
    public void onPersist(PersistEvent event) throws HibernateException {
        serialize(event.getObject());
    }

    @Override
    public void onPersist(PersistEvent event, Map createdAlready) throws HibernateException {
        serialize(event.getObject());
    }

    private void serialize(Object entity) {
        if (SerializationContainer.class.isAssignableFrom(entity.getClass())) {
            try {
                ((SerializationContainer) entity).serialize(objectMapper);
            } catch (JsonProcessingException e) {
                // TODO custom exception should be created
                throw new RuntimeException(e);
            }
        }
    }

}

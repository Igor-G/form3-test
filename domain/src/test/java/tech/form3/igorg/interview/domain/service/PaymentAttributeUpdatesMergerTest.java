package tech.form3.igorg.interview.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import tech.form3.igorg.interview.model.payment.ChargesInformation;
import tech.form3.igorg.interview.model.payment.PaymentAttributes;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Unit tests for {@link PaymentAttributeUpdatesMerger}.
 */
public class PaymentAttributeUpdatesMergerTest {

    private PaymentAttributeUpdatesMerger attributeUpdatesMerger = new PaymentAttributeUpdatesMerger(new ObjectMapper());

    @Test
    public void shouldMergeNonExistingFields() {
        // given
        Map<String, Object> charge = new HashMap<>();
        charge.put("amount", 234);
        List<Map<String, Object>> senderCharges = Collections.singletonList(charge);
        Map<String, Object> chargesInformation = new HashMap<>();
        chargesInformation.put("sender_charges", senderCharges);
        Map<String, Object> attributeUpdates = new HashMap<>();
        attributeUpdates.put("charges_information", chargesInformation);

        PaymentAttributes existingAttributes = new PaymentAttributes();

        // when
        PaymentAttributes mergedAttributes =
                attributeUpdatesMerger.mergeAttributeUpdates(existingAttributes, attributeUpdates);

        // then
        assertThat(mergedAttributes.getChargesInformation().getSenderCharges().size(), equalTo(1));
        assertThat(mergedAttributes.getChargesInformation().getSenderCharges().get(0).getAmount(), equalTo(new BigDecimal("234")));
    }

    @Test
    public void shouldMergeAttributesWithoutRemovingOldOnes() {
        // given
        Map<String, Object> charge = new HashMap<>();
        charge.put("amount", 234);
        List<Map<String, Object>> senderCharges = Collections.singletonList(charge);
        Map<String, Object> chargesInformationMap = new HashMap<>();
        chargesInformationMap.put("sender_charges", senderCharges);
        Map<String, Object> attributeUpdates = new HashMap<>();
        attributeUpdates.put("charges_information", chargesInformationMap);

        PaymentAttributes existingAttributes = new PaymentAttributes();
        existingAttributes.setAmount(new BigDecimal("123"));
        ChargesInformation chargesInformation = new ChargesInformation();
        chargesInformation.setReceiverChargesAmount(new BigDecimal("555"));
        existingAttributes.setChargesInformation(chargesInformation);

        // when
        PaymentAttributes mergedAttributes =
                attributeUpdatesMerger.mergeAttributeUpdates(existingAttributes, attributeUpdates);

        // then
        assertThat(mergedAttributes.getAmount(), equalTo(new BigDecimal("123")));
        assertThat(mergedAttributes.getChargesInformation().getSenderCharges().size(), equalTo(1));
        assertThat(mergedAttributes.getChargesInformation().getSenderCharges().get(0).getAmount(), equalTo(new BigDecimal("234")));
        assertThat(mergedAttributes.getChargesInformation().getReceiverChargesAmount(), equalTo(new BigDecimal("555")));
    }

    @Test
    public void shouldMergeAttributeAndSetToNull() {
        // given
        Map<String, Object> chargesInformationMap = new HashMap<>();
        chargesInformationMap.put("receiver_charges_amount", null);
        Map<String, Object> attributeUpdates = new HashMap<>();
        attributeUpdates.put("charges_information", chargesInformationMap);

        PaymentAttributes existingAttributes = new PaymentAttributes();
        ChargesInformation chargesInformation = new ChargesInformation();
        chargesInformation.setReceiverChargesAmount(new BigDecimal("555"));
        existingAttributes.setChargesInformation(chargesInformation);

        // when
        PaymentAttributes mergedAttributes =
                attributeUpdatesMerger.mergeAttributeUpdates(existingAttributes, attributeUpdates);

        // then
        assertThat(mergedAttributes.getChargesInformation().getReceiverChargesAmount(), is(nullValue()));
    }

}
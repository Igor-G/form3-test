package tech.form3.igorg.interview.model.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.form3.igorg.interview.model.serialization.SerializationContainer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;

/**
 * Payment model.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment implements SerializationContainer {

    @Id
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "organization_id")
    private String organizationId;

    @Valid
    @Transient
    private transient PaymentAttributes paymentAttributes;

    @Column(name = "payment_attributes_serialized")
    private String paymentAttributesSerialized;

    @Override
    public void serialize(ObjectMapper objectMapper) throws JsonProcessingException {
        if (paymentAttributes != null) {
            paymentAttributesSerialized = objectMapper.writeValueAsString(paymentAttributes);
        }
    }

    @Override
    public void deserialize(ObjectMapper objectMapper) throws IOException {
        if (paymentAttributesSerialized != null) {
            paymentAttributes = objectMapper.readValue(paymentAttributesSerialized, PaymentAttributes.class);
        }
    }
}

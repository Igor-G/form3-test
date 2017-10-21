package tech.form3.igorg.interview.model.payment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("id")
    private String id = UUID.randomUUID().toString();

    @Version
    @Column(name = "version")
    @JsonProperty("version")
    private Integer version;

    @Column(name = "organization_id")
    @JsonProperty("organization_id")
    private String organizationId;

    @Valid
    @Transient
    @JsonProperty("attributes")
    private transient PaymentAttributes attributes;

    @JsonIgnore
    @Column(name = "payment_attributes_serialized")
    private String paymentAttributesSerialized;

    public Payment(Payment payment) {
        this.id = payment.getId();
        this.version = payment.getVersion();
        this.organizationId = payment.getOrganizationId();
        this.attributes = payment.getAttributes();
    }

    @Override
    public void serialize(ObjectMapper objectMapper) throws JsonProcessingException {
        if (attributes != null) {
            paymentAttributesSerialized = objectMapper.writeValueAsString(attributes);
        }
    }

    @Override
    public void deserialize(ObjectMapper objectMapper) throws IOException {
        if (paymentAttributesSerialized != null) {
            attributes = objectMapper.readValue(paymentAttributesSerialized, PaymentAttributes.class);
        }
    }
}

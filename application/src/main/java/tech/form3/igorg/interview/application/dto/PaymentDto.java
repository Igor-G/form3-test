package tech.form3.igorg.interview.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.form3.igorg.interview.model.payment.Payment;
import tech.form3.igorg.interview.model.payment.PaymentAttributes;

/**
 * Payment DTO.
 */
@Data
@NoArgsConstructor
public class PaymentDto {

    private final String type = "Payment";

    private String id;

    private Integer version;

    @JsonProperty("organization_id")
    private String organizationId;

    private PaymentAttributes attributes;

    public PaymentDto(Payment payment) {
        this.id = payment.getId();
        this.version = payment.getVersion();
        this.organizationId = payment.getOrganizationId();
        this.attributes = payment.getPaymentAttributes();
    }

}

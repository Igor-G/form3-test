package tech.form3.igorg.interview.model.payment;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.form3.igorg.interview.model.payment.enums.PaymentType;

/**
 * Payment model.
 */
@Data
@NoArgsConstructor
public class Payment {

    private PaymentType paymentType;

    private String id;

    private Integer version;

    private String organizationId;

    private PaymentAttributes paymentAttributes;
}

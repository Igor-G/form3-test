package tech.form3.igorg.interview.application.api.v1.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Identifiable;
import tech.form3.igorg.interview.model.payment.Payment;

/**
 * Payment resource.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PaymentResource extends Payment implements Identifiable<String> {

    private final String type = "Payment";

    public PaymentResource(Payment payment) {
        super(payment);
    }
}

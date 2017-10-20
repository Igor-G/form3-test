package tech.form3.igorg.interview.application.api.v1.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Identifiable;
import org.springframework.hateoas.Link;
import org.springframework.util.Assert;
import tech.form3.igorg.interview.application.dto.PaymentDto;
import tech.form3.igorg.interview.model.payment.Payment;

import java.util.ArrayList;
import java.util.List;

/**
 * Payment resource.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PaymentResource extends PaymentDto implements Identifiable<String> {

    private final List<Link> links = new ArrayList<>();

    public PaymentResource(Payment payment) {
        super(payment);
    }

    /**
     * Adds the given link to the resource.
     *
     * @param link the link
     */
    public void add(Link link) {
        Assert.notNull(link, "Link must not be null!");
        this.links.add(link);
    }

}

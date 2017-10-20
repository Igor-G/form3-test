package tech.form3.igorg.interview.application.api.v1.resource;

import lombok.Getter;
import org.springframework.hateoas.Link;
import org.springframework.util.Assert;
import tech.form3.igorg.interview.application.dto.PaymentDto;
import tech.form3.igorg.interview.model.payment.Payment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Payments resource.
 */
@Getter
public class PaymentsResource {

    private final List<Link> links = new ArrayList<>();

    private List<PaymentDto> data;

    public PaymentsResource(List<Payment> payments) {
        data = payments.stream()
                .map(PaymentDto::new)
                .collect(Collectors.toList());
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

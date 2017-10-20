package tech.form3.igorg.interview.application.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tech.form3.igorg.interview.application.api.v1.resource.PaymentResource;
import tech.form3.igorg.interview.model.payment.Payment;

/**
 * PaymentResource to Payment converter.
 */
@Component
public class PaymentResourceConverter implements Converter<PaymentResource, Payment> {
    @Override
    public Payment convert(PaymentResource paymentResource) {
        Payment payment = new Payment();
        payment.setId(paymentResource.getId());
        payment.setVersion(paymentResource.getVersion());
        payment.setOrganizationId(paymentResource.getOrganizationId());
        payment.setPaymentAttributes(paymentResource.getAttributes());
        return payment;
    }
}

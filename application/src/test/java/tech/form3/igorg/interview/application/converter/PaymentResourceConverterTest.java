package tech.form3.igorg.interview.application.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import tech.form3.igorg.interview.application.api.v1.resource.PaymentResource;
import tech.form3.igorg.interview.model.payment.Payment;
import tech.form3.igorg.interview.model.payment.PaymentAttributes;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Unit tests for {@link PaymentResourceConverter}.
 */
@RunWith(MockitoJUnitRunner.class)
public class PaymentResourceConverterTest {

    private PaymentResourceConverter paymentResourceConverter = new PaymentResourceConverter();

    @Test
    public void shouldConvertPaymentResourceToPayment() {
        // given
        PaymentResource paymentResource = new PaymentResource();
        paymentResource.setOrganizationId("org-id");
        paymentResource.setVersion(1);
        paymentResource.setId("id");
        paymentResource.setAttributes(new PaymentAttributes());

        // when
        Payment payment = paymentResourceConverter.convert(paymentResource);

        // then
        assertThat(payment.getId(), equalTo(paymentResource.getId()));
        assertThat(payment.getOrganizationId(), equalTo(paymentResource.getOrganizationId()));
        assertThat(payment.getPaymentAttributes(), equalTo(paymentResource.getAttributes()));
        assertThat(payment.getVersion(), equalTo(paymentResource.getVersion()));
    }
}
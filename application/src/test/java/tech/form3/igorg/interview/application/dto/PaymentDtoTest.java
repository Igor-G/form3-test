package tech.form3.igorg.interview.application.dto;

import org.junit.Test;
import tech.form3.igorg.interview.model.payment.Payment;
import tech.form3.igorg.interview.model.payment.PaymentAttributes;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Unit tests for {@link PaymentDto}.
 */
public class PaymentDtoTest {

    @Test
    public void shouldCreateDtoFromPayment() {
        // given
        Payment payment = new Payment();
        payment.setVersion(3);
        payment.setOrganizationId("some-org-id");
        payment.setPaymentAttributes(new PaymentAttributes());

        // when
        PaymentDto paymentDto = new PaymentDto(payment);

        // then
        assertThat(paymentDto.getId(), equalTo(payment.getId()));
        assertThat(paymentDto.getVersion(), equalTo(payment.getVersion()));
        assertThat(paymentDto.getOrganizationId(), equalTo(payment.getOrganizationId()));
        assertThat(paymentDto.getAttributes(), equalTo(payment.getPaymentAttributes()));
    }
}
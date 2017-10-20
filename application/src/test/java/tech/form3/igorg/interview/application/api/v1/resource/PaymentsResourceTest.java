package tech.form3.igorg.interview.application.api.v1.resource;

import org.junit.Test;
import tech.form3.igorg.interview.model.payment.Payment;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Unit tests for {@link PaymentsResource}.
 */
public class PaymentsResourceTest {

    @Test
    public void shouldCreatePaymentsResource() {
        // given
        List<Payment> payments = asList(new Payment(), new Payment());

        // when
        PaymentsResource paymentsResource = new PaymentsResource(payments);

        // then
        assertThat(paymentsResource.getData().size(), equalTo(2));
        assertThat(paymentsResource.getData().get(0).getId(), equalTo(payments.get(0).getId()));
        assertThat(paymentsResource.getData().get(1).getId(), equalTo(payments.get(1).getId()));
    }
}
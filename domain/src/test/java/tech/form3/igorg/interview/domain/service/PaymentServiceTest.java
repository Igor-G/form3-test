package tech.form3.igorg.interview.domain.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tech.form3.igorg.interview.infrastructure.repository.PaymentRepository;
import tech.form3.igorg.interview.model.payment.Payment;
import tech.form3.igorg.interview.model.payment.PaymentAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.apache.commons.collections4.CollectionUtils.isEqualCollection;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for {@link PaymentService}.
 */
@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentAttributeUpdatesMerger attributeUpdatesMerger;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    public void shouldCreateNewEmptyPayment() {
        // given
        Payment payment = new Payment();
        payment.setOrganizationId("org-id");
        payment.setAttributes(new PaymentAttributes());
        given(paymentRepository.save(any(Payment.class))).willAnswer(returnsFirstArg());

        // when
        Payment newPayment = paymentService.createNewPayment(payment);

        // then
        verify(paymentRepository).save(newPayment);
        assertThat(newPayment.getOrganizationId(), equalTo("org-id"));
        assertThat(newPayment.getAttributes(), equalTo(payment.getAttributes()));
    }

    @Test
    public void shouldGetPayment() {
        // given
        String paymentId = "some-payment-id";
        Payment mockPayment = mock(Payment.class);
        given(paymentRepository.findOne(paymentId)).willReturn(mockPayment);

        // when
        Payment payment = paymentService.getPayment(paymentId);

        // then
        assertThat(payment, sameInstance(mockPayment));
        verify(paymentRepository).findOne(paymentId);
    }

    @Test
    public void shouldGetAllPayments() {
        // given
        List<Payment> mockPayments = asList(mock(Payment.class), mock(Payment.class));
        given(paymentRepository.findAll()).willReturn(mockPayments);

        // when
        List<Payment> payments = paymentService.getAllPayments();

        // then
        assertTrue(isEqualCollection(payments, mockPayments));
        verify(paymentRepository).findAll();
    }

    @Test
    public void shouldUpdatePayment() {
        // given
        Payment paymentFromDb = new Payment();
        paymentFromDb.setOrganizationId("old-org-id");

        String paymentId = paymentFromDb.getId();
        Integer version = 1;

        Map<String, Object> attributeUpdates = new HashMap<>();
        PaymentAttributes paymentAttributes = new PaymentAttributes();

        given(attributeUpdatesMerger.mergeAttributeUpdates(paymentFromDb.getAttributes(), attributeUpdates)).willReturn(paymentAttributes);
        given(paymentRepository.findOne(paymentId, version)).willReturn(paymentFromDb);
        given(paymentRepository.save(any(Payment.class))).willAnswer(returnsFirstArg());

        // when
        Payment payment = paymentService.updatePayment(paymentId, version, attributeUpdates);

        // then
        assertThat(payment, sameInstance(paymentFromDb));
        assertThat(payment.getAttributes(), sameInstance(paymentAttributes));
        verify(paymentRepository).findOne(paymentId, version);
        verify(attributeUpdatesMerger).mergeAttributeUpdates(null, attributeUpdates);
        verify(paymentRepository).save(paymentFromDb);
    }

    @Test
    public void shouldDeletePayment() {
        // given
        String paymentId = "some-payment-id";

        // when
        paymentService.deletePayment(paymentId);

        // then
        verify(paymentRepository).delete(paymentId);
    }
}
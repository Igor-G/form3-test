package tech.form3.igorg.interview.domain.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tech.form3.igorg.interview.infrastructure.repository.PaymentRepository;
import tech.form3.igorg.interview.model.payment.Payment;

import java.util.List;

import static java.util.Arrays.asList;
import static org.apache.commons.collections4.CollectionUtils.isEqualCollection;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
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

    @InjectMocks
    private PaymentService paymentService;

    @Test
    public void shouldCreateNewEmptyPayment() {
        // given
        Payment mockPayment = mock(Payment.class);
        given(paymentRepository.save(any(Payment.class))).willReturn(mockPayment);

        // when
        Payment payment = paymentService.createNewPayment();

        // then
        assertThat(payment, sameInstance(mockPayment));
        verify(paymentRepository).save(any(Payment.class));
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
        Payment mockPayment = mock(Payment.class);
        Payment mockReturnPayment = mock(Payment.class);
        given(paymentRepository.save(mockPayment)).willReturn(mockReturnPayment);

        // when
        Payment payment = paymentService.updatePayment(mockPayment);

        // then
        assertThat(payment, sameInstance(mockReturnPayment));
        verify(paymentRepository).save(mockPayment);
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
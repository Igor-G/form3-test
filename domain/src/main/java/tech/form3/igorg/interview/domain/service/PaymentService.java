package tech.form3.igorg.interview.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.form3.igorg.interview.infrastructure.repository.PaymentRepository;
import tech.form3.igorg.interview.model.payment.Payment;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Payment domain service.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentService {

    private final PaymentRepository paymentRepository;

    /**
     * Creates a new (empty) payment.
     *
     * @return the newly created payment
     */
    public Payment createNewPayment() {
        return paymentRepository.save(new Payment());
    }

    /**
     * Gets a payment by the provided id.
     *
     * @param paymentId the payment id
     * @return the payment with the provided id.
     */
    public Payment getPayment(String paymentId) {
        return paymentRepository.findOne(paymentId);
    }

    /**
     * Gets all available payments.
     *
     * @return list of all payments
     */
    // TODO check if should fetch only payments from a certain group (e.g. organization)
    public List<Payment> getAllPayments() {
        return newArrayList(paymentRepository.findAll());
    }

    /**
     * Updates the provided payment.
     *
     * @param payment the payment containing the updates
     * @return the updated (saved) payment
     */
    public Payment updatePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public void deletePayment(String paymentId) {
        paymentRepository.delete(paymentId);
    }

}

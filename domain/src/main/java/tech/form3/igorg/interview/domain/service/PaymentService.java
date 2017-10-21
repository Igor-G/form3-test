package tech.form3.igorg.interview.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.form3.igorg.interview.infrastructure.repository.PaymentRepository;
import tech.form3.igorg.interview.model.payment.Payment;
import tech.form3.igorg.interview.model.payment.PaymentAttributes;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Payment domain service.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final PaymentAttributeUpdatesMerger attributeUpdatesMerger;

    /**
     * Creates a new (empty) payment.
     *
     * @return the newly created payment
     */
    public Payment createNewPayment(Payment payment) {
        Payment newPayment = new Payment();
        newPayment.setOrganizationId(payment.getOrganizationId());
        newPayment.setAttributes(payment.getAttributes());
        return paymentRepository.save(newPayment);
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
    public List<Payment> getAllPayments() {
        return newArrayList(paymentRepository.findAll());
    }

    /**
     * Updates the provided payment.
     *
     * @param attributeUpdates the map containing the attribute updates
     * @return the updated (saved) payment
     */
    public Payment updatePayment(String paymentId, Integer paymentVersion, Map<String, Object> attributeUpdates) {
        Payment payment = paymentRepository.findOne(paymentId, paymentVersion);
        PaymentAttributes updatedAttributes =
                attributeUpdatesMerger.mergeAttributeUpdates(payment.getAttributes(), attributeUpdates);
        payment.setAttributes(updatedAttributes);
        return paymentRepository.save(payment);
    }

    public void deletePayment(String paymentId) {
        paymentRepository.delete(paymentId);
    }

}

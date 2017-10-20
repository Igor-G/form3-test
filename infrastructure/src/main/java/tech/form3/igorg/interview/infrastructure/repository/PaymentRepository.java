package tech.form3.igorg.interview.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import tech.form3.igorg.interview.model.payment.Payment;

/**
 * Payment repository.
 */
public interface PaymentRepository extends CrudRepository<Payment, String> {
}
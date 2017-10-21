package tech.form3.igorg.interview.infrastructure.repository.impl;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import tech.form3.igorg.interview.infrastructure.repository.PaymentRepositoryCustom;
import tech.form3.igorg.interview.model.exception.Form3EntityNotFoundException;
import tech.form3.igorg.interview.model.exception.Form3OptimisticLockException;
import tech.form3.igorg.interview.model.payment.Payment;

import javax.persistence.EntityManager;

/**
 * Payment repository custom implementation.
 */
public class PaymentRepositoryImpl extends SimpleJpaRepository<Payment, String>
                                                implements PaymentRepositoryCustom {

    public PaymentRepositoryImpl(EntityManager em) {
        super(Payment.class, em);
    }

    @Override
    public Payment findOne(String id) {
        Payment fromDb = super.findOne(id);
        if (fromDb == null) {
            throw new Form3EntityNotFoundException("Payment", id);
        }
        return fromDb;
    }

    @Override
    public Payment findOne(String id, Integer version) {
        Payment fromDb = findOne(id);
        if (!fromDb.getVersion().equals(version)) {
            throw new Form3OptimisticLockException("Payment", id);
        }
        return fromDb;
    }
}

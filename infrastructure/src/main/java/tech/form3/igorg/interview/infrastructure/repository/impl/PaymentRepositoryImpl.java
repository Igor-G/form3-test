package tech.form3.igorg.interview.infrastructure.repository.impl;

import org.springframework.dao.OptimisticLockingFailureException;
import tech.form3.igorg.interview.infrastructure.repository.PaymentRepositoryCustom;
import tech.form3.igorg.interview.model.payment.Payment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Payment repository custom implementation.
 */
public class PaymentRepositoryImpl implements PaymentRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Payment findOne(String id, Integer version) {
        Payment fromDb = em.find(Payment.class, id);
        if (!fromDb.getVersion().equals(version)) {
            throw new OptimisticLockingFailureException("A new version of the 'Payment' is available");
        }
        return fromDb;
    }
}

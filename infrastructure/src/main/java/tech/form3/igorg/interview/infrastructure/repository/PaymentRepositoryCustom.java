package tech.form3.igorg.interview.infrastructure.repository;

import tech.form3.igorg.interview.model.payment.Payment;

/**
 * Custom payment repository.
 */
public interface PaymentRepositoryCustom {

    Payment findOne(String id, Integer version);

}

package tech.form3.igorg.interview.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import tech.form3.igorg.interview.model.payment.Payment;

/**
 * Custom payment repository.
 */
@NoRepositoryBean
public interface PaymentRepositoryCustom extends CrudRepository<Payment, String> {

    /**
     * Finds the Payment with the provided id and checks the version.
     *
     * @param id      the id of the payment
     * @param version the version
     * @return the Payment with the provided id
     */
    Payment findOne(String id, Integer version);

}

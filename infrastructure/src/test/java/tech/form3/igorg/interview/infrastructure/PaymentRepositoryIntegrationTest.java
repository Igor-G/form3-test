package tech.form3.igorg.interview.infrastructure;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tech.form3.igorg.interview.infrastructure.config.Form3InfrastructureConfig;
import tech.form3.igorg.interview.infrastructure.repository.PaymentRepository;
import tech.form3.igorg.interview.model.payment.BeneficiaryParty;
import tech.form3.igorg.interview.model.payment.Payment;
import tech.form3.igorg.interview.model.payment.PaymentAttributes;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


/**
 * Payment repository integration test.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {Form3InfrastructureConfig.class, InfrastructureTestConfig.class})
@Transactional
public class PaymentRepositoryIntegrationTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldSavePayment() {
        // given
        BigDecimal amount = new BigDecimal("23.43");
        String accountName = "some-account-name";
        Payment payment = new Payment();
        PaymentAttributes paymentAttributes = new PaymentAttributes();
        paymentAttributes.setAmount(amount);
        BeneficiaryParty beneficiaryParty = new BeneficiaryParty();
        beneficiaryParty.setAccountName(accountName);
        paymentAttributes.setBeneficiaryParty(beneficiaryParty);
        payment.setPaymentAttributes(paymentAttributes);

        // when
        Payment savedPayment = paymentRepository.save(payment);

        // then
        assertThat(savedPayment.getId(), equalTo(payment.getId()));
        assertThat(savedPayment.getPaymentAttributes().getAmount(), equalTo(amount));
        assertThat(savedPayment.getPaymentAttributes().getBeneficiaryParty().getAccountName(), equalTo(accountName));
    }

    @Test
    public void shouldGetPayment() {
        // given
        Payment payment = createAndSavePayment();

        // when
        Payment paymentFromDb = paymentRepository.findOne(payment.getId());

        // then
        assertThat(paymentFromDb, equalTo(payment));
    }

    @Test
    public void shouldGetPaymentAndCheckVersionSuccessfully() {
        // given
        Payment payment = createAndSavePayment();

        // when
        Payment paymentFromDb = paymentRepository.findOne(payment.getId(), 0);

        // then
        assertThat(paymentFromDb, equalTo(payment));
    }

    @Test
    public void shouldGetPaymentAndFailOnVersionMismatch() {
        // given
        Payment payment = createAndSavePayment();
        expectedException.expect(OptimisticLockingFailureException.class);
        expectedException.expectMessage("A new version of the 'Payment' is available");

        // when
        paymentRepository.findOne(payment.getId(), 1);

        // then
        // expect exception to be thrown
    }

    @Test
    public void shouldUpdatePayment() {
        // given
        Payment payment = createAndSavePayment();
        BigDecimal newAmount = new BigDecimal("11.23");
        payment.getPaymentAttributes().setAmount(newAmount);

        // when
        Payment paymentFromDb = paymentRepository.save(payment);

        // then
        assertThat(paymentFromDb.getPaymentAttributes().getAmount(), equalTo(newAmount));
    }

    @Test
    public void shouldDeletePayment() {
        // given
        Payment payment = createAndSavePayment();

        // when
        paymentRepository.delete(payment.getId());

        // then
        assertThat(paymentRepository.findOne(payment.getId()), is(nullValue()));
    }

    private Payment createAndSavePayment() {
        Payment payment = new Payment();
        PaymentAttributes paymentAttributes = new PaymentAttributes();
        BigDecimal amount = new BigDecimal("23.43");
        paymentAttributes.setAmount(amount);
        payment.setPaymentAttributes(paymentAttributes);
        return paymentRepository.save(payment);
    }

}

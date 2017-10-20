package tech.form3.igorg.interview.model.payment;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

/**
 * Payment attributes.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "payment_attributes")
public class PaymentAttributes {

    @Id
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();

    @Column(name = "amount")
    private BigDecimal amount;

    @Embedded
    private BeneficiaryParty beneficiaryParty;

    @Column(name = "currency")
    private Currency currency;

}

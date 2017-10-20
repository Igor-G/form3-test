package tech.form3.igorg.interview.model.payment;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Payment attributes.
 */
@Data
@NoArgsConstructor
public class PaymentAttributes {

    private BigDecimal amount;

    private BeneficiaryParty beneficiaryParty;

    private Currency currency;

}

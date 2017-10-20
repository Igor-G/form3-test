package tech.form3.igorg.interview.model.payment;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.form3.igorg.interview.model.payment.enums.AccountNumberCode;
import tech.form3.igorg.interview.model.payment.enums.AccountType;
import tech.form3.igorg.interview.model.payment.enums.BankIdCode;

import java.math.BigDecimal;

/**
 * Beneficiary party.
 */
@Data
@NoArgsConstructor
public class BeneficiaryParty {

    private String accountName;

    private BigDecimal accountNumber;

    private AccountNumberCode accountNumberCode;

    private AccountType accountType;

    private String address;

    private BigDecimal bankId;

    private BankIdCode bankIdCode;

    private String name;

}

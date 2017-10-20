package tech.form3.igorg.interview.model.payment;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.form3.igorg.interview.model.payment.enums.AccountNumberCode;
import tech.form3.igorg.interview.model.payment.enums.AccountType;
import tech.form3.igorg.interview.model.payment.enums.BankIdCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

/**
 * Beneficiary party.
 */
@Data
@NoArgsConstructor
@Embeddable
public class BeneficiaryParty {

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_number")
    private BigDecimal accountNumber;

    @Column(name = "account_number_code")
    private AccountNumberCode accountNumberCode;

    @Column(name = "account_type")
    private AccountType accountType;

    @Column(name = "address")
    private String address;

    @Column(name = "bank_id")
    private BigDecimal bankId;

    @Column(name = "bank_id_code")
    private BankIdCode bankIdCode;

    @Column(name = "name")
    private String name;

}

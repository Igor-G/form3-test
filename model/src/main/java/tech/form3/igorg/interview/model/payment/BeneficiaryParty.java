package tech.form3.igorg.interview.model.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("account_name")
    private String accountName;

    @JsonProperty("account_number")
    private BigDecimal accountNumber;

    @JsonProperty("account_number_code")
    private AccountNumberCode accountNumberCode;

    @JsonProperty("account_type")
    private AccountType accountType;

    @JsonProperty("address")
    private String address;

    @JsonProperty("bank_id")
    private BigDecimal bankId;

    @JsonProperty("bank_id_code")
    private BankIdCode bankIdCode;

    @JsonProperty("name")
    private String name;

}

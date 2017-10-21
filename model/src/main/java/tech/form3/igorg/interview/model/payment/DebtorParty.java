package tech.form3.igorg.interview.model.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.form3.igorg.interview.model.payment.enums.AccountNumberCode;
import tech.form3.igorg.interview.model.payment.enums.BankIdCode;

import java.math.BigDecimal;

/**
 * Debtor party.
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DebtorParty {

    @JsonProperty("account_name")
    private String accountName;

    @JsonProperty("account_number")
    private BigDecimal accountNumber;

    @JsonProperty("account_number_code")
    private AccountNumberCode accountNumberCode;

    @JsonProperty("address")
    private String address;

    @JsonProperty("bank_id")
    private BigDecimal bankId;

    @JsonProperty("bank_id_code")
    private BankIdCode bankIdCode;

    @JsonProperty("name")
    private String name;

}

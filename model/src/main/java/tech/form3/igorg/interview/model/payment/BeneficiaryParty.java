package tech.form3.igorg.interview.model.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.form3.igorg.interview.model.payment.enums.AccountType;

/**
 * Beneficiary party.
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BeneficiaryParty extends DebtorParty {

    @JsonProperty("account_type")
    private AccountType accountType;

}

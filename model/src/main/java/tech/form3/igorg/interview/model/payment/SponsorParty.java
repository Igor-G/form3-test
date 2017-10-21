package tech.form3.igorg.interview.model.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.form3.igorg.interview.model.payment.enums.BankIdCode;

import java.math.BigDecimal;

/**
 * Sponsor party.
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SponsorParty {

    @JsonProperty("account_number")
    private BigDecimal accountNumber;

    @JsonProperty("bank_id")
    private BigDecimal bankId;

    @JsonProperty("bank_id_code")
    private BankIdCode bankIdCode;

}

package tech.form3.igorg.interview.model.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.form3.igorg.interview.model.payment.enums.BearerCode;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

/**
 * Charges information.
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChargesInformation {

    @JsonProperty("bearer_code")
    private BearerCode bearerCode;

    @JsonProperty("sender_charges")
    private List<Charge> senderCharges;

    @JsonProperty("receiver_charges_amount")
    private BigDecimal receiverChargesAmount;

    @JsonProperty("receiver_charges_currency")
    private Currency receiverChargesCurrency;

}

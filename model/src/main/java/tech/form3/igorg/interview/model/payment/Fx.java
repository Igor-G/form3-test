package tech.form3.igorg.interview.model.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Fx.
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Fx {

    @JsonProperty("contract_reference")
    private String contractReference;

    @JsonProperty("exchange_rate")
    private BigDecimal exchangeRate;

    @JsonProperty("original_amount")
    private BigDecimal originalAmount;

    @JsonProperty("original_currency")
    private Currency originalCurrency;
}

package tech.form3.igorg.interview.model.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Charge.
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Charge {

    private BigDecimal amount;

    private Currency currency;

}

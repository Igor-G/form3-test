package tech.form3.igorg.interview.model.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

/**
 * Payment attributes.
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentAttributes {

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("beneficiary_party")
    private BeneficiaryParty beneficiaryParty;

    @JsonProperty("charges_information")
    private ChargesInformation chargesInformation;

    @JsonProperty("currency")
    private Currency currency;

    @JsonProperty("debtor_party")
    private DebtorParty debtorParty;

    @JsonProperty("end_to_end_reference")
    private String endToEndReference;

    @JsonProperty("fx")
    private Fx fx;

    @JsonProperty("numeric_reference")
    private String numericReference;

    @JsonProperty("payment_id")
    private String paymentId;

    @JsonProperty("payment_purpose")
    private String paymentPurpose;

    @JsonProperty("payment_scheme")
    private String paymentScheme;

    @JsonProperty("payment_type")
    private String paymentType;

    @JsonProperty("processing_date")
    private LocalDate processingDate;

    @JsonProperty("reference")
    private String reference;

    @JsonProperty("scheme_payment_sub_type")
    private String schemePaymentSubType;

    @JsonProperty("scheme_payment_type")
    private String schemePaymentType;

    @JsonProperty("sponsor_party")
    private SponsorParty sponsorParty;
}

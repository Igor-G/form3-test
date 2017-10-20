package tech.form3.igorg.interview.model.payment;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.form3.igorg.interview.model.payment.enums.PaymentType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.UUID;

/**
 * Payment model.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    private PaymentType paymentType;

    @Id
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();

    // TODO ig: check version
    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "organization_id")
    private String organizationId;

    // TODO make it a json (serialized as string)
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_attributes_id", unique = true, updatable = false)
    private PaymentAttributes paymentAttributes;
}

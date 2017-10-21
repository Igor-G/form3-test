package tech.form3.igorg.interview.application.api.v1.resource;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Payment update resource.
 */
@Data
@NoArgsConstructor
public class PaymentUpdateResource {

    @NotNull
    private String id;

    @NotNull
    private String type;

    @NotNull
    private Integer version;

    private Map<String, Object> attributes;
}

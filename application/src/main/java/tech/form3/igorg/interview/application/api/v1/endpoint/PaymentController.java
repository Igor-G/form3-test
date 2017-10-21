package tech.form3.igorg.interview.application.api.v1.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.form3.igorg.interview.application.api.v1.resource.PaymentResource;
import tech.form3.igorg.interview.application.api.v1.resource.RestResource;
import tech.form3.igorg.interview.domain.service.PaymentService;
import tech.form3.igorg.interview.model.payment.Payment;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Payments controller.
 */
@RestController
@RequestMapping("/v1/payments")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentController {

    private final PaymentService paymentService;

    private final ConversionService conversionService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/")
    public RestResource<PaymentResource> createPayment(
            @RequestBody RestResource<PaymentResource> paymentResourceRestResource) {
        Payment payment = conversionService.convert(paymentResourceRestResource.getData(), Payment.class);
        RestResource<PaymentResource> paymentResource =
                new RestResource<>(new PaymentResource(paymentService.createNewPayment(payment)));

        paymentResource.add(linkTo(methodOn(PaymentController.class).createPayment(paymentResource)).withSelfRel());
        return paymentResource;
    }

    @GetMapping("/{paymentId}")
    public RestResource<PaymentResource> getPayment(@PathVariable("paymentId") String paymentId) {
        RestResource<PaymentResource> paymentResource =
                new RestResource<>(new PaymentResource(paymentService.getPayment(paymentId)));
        paymentResource.add(linkTo(methodOn(PaymentController.class).getPayment(paymentId)).withSelfRel());
        return paymentResource;
    }

    @GetMapping("/")
    public RestResource<List<PaymentResource>> getAllPayments() {
        List<PaymentResource> paymentsResource = paymentService.getAllPayments().stream()
                                                                        .map(PaymentResource::new)
                                                                        .collect(Collectors.toList());
        RestResource<List<PaymentResource>> paymentResource = new RestResource<>(paymentsResource);
        paymentResource.add(linkTo(methodOn(PaymentController.class).getAllPayments()).withSelfRel());
        return paymentResource;
    }

    @PatchMapping("/{paymentId}")
    public RestResource<PaymentResource> updatePayment(@PathVariable("paymentId") String paymentId,
                                         @RequestBody PaymentResource paymentResource) {
        Payment payment = conversionService.convert(paymentResource, Payment.class);
        RestResource<PaymentResource> updatedPaymentResource =
                new RestResource<>(new PaymentResource(paymentService.updatePayment(payment)));
        updatedPaymentResource.add(linkTo(methodOn(PaymentController.class)
                .updatePayment(paymentId, paymentResource)).withSelfRel());
        return updatedPaymentResource;
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{paymentId}")
    public void deletePayment(@PathVariable("paymentId") String paymentId) {
        paymentService.deletePayment(paymentId);
    }

}

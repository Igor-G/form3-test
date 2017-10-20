package tech.form3.igorg.interview.application.api.v1.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.form3.igorg.interview.application.api.v1.resource.PaymentResource;
import tech.form3.igorg.interview.application.api.v1.resource.PaymentsResource;
import tech.form3.igorg.interview.domain.service.PaymentService;
import tech.form3.igorg.interview.model.payment.Payment;

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
    public PaymentResource createPayment() {
        PaymentResource paymentResource = new PaymentResource(paymentService.createNewPayment());
        paymentResource.add(linkTo(methodOn(PaymentController.class).createPayment()).withSelfRel());
        return paymentResource;
    }

    @GetMapping("/{paymentId}")
    public PaymentResource getPayment(@PathVariable("paymentId") String paymentId) {
        PaymentResource paymentResource = new PaymentResource(paymentService.getPayment(paymentId));
        paymentResource.add(linkTo(methodOn(PaymentController.class).getPayment(paymentId)).withSelfRel());
        return paymentResource;
    }

    @GetMapping("/")
    public PaymentsResource getAllPayments() {
        PaymentsResource paymentResource = new PaymentsResource(paymentService.getAllPayments());
        paymentResource.add(linkTo(methodOn(PaymentController.class).getAllPayments()).withSelfRel());
        return paymentResource;
    }

    @PutMapping("/{paymentId}")
    public PaymentResource updatePayment(@PathVariable("paymentId") String paymentId,
                                         @RequestBody PaymentResource paymentResource) {
        Payment payment = conversionService.convert(paymentResource, Payment.class);
        PaymentResource updatedPaymentResource = new PaymentResource(paymentService.updatePayment(payment));
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
package com.aurelius.tech.paymentrestservice.controller;


import com.aurelius.tech.paymentrestservice.model.PaymentAcceptedResponse;
import com.aurelius.tech.paymentrestservice.model.PaymentCreateRequest;
import com.aurelius.tech.paymentrestservice.model.PaymentRequest;
import com.aurelius.tech.paymentrestservice.service.PaymentCommandService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("1.0")
public class PaymentRestController {


    private final PaymentCommandService paymentCommandService;


    public PaymentRestController(PaymentCommandService paymentCommandService) {
        this.paymentCommandService = paymentCommandService;

    }



    @PostMapping("/payments")
    public ResponseEntity<PaymentAcceptedResponse> createPayment(
            @RequestBody @Valid PaymentCreateRequest request,
            @RequestHeader(value = "X-Correlation-Id", required = false) String correlationId
    ) {
        String corrId = (correlationId == null || correlationId.isBlank())
                ? UUID.randomUUID().toString()
                : correlationId;

        PaymentAcceptedResponse response = paymentCommandService.acceptPayment(request, corrId);

        // 202 because actual processing happens asynchronously in Payment Process Service
        return ResponseEntity.accepted().body(response);
    }



}

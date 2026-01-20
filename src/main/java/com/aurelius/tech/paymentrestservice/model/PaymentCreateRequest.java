package com.aurelius.tech.paymentrestservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PaymentCreateRequest(
        @NotBlank String orderId,
        @NotNull BigDecimal amount,
        @NotBlank String currency,
        @NotBlank String paymentMethod,
        @NotBlank String customerId
) {}

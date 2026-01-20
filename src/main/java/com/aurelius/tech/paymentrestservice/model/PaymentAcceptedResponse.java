package com.aurelius.tech.paymentrestservice.model;

public record PaymentAcceptedResponse(
        String paymentId,
        String status,       // PENDING
        String correlationId
) {}
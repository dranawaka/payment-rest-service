package com.aurelius.tech.paymentrestservice.service;

import com.aurelius.tech.paymentrestservice.model.PaymentAcceptedResponse;
import com.aurelius.tech.paymentrestservice.model.PaymentCreateRequest;

public interface PaymentCommandService {
    PaymentAcceptedResponse acceptPayment(PaymentCreateRequest request, String correlationId);
}
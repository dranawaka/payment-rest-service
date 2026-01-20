package com.aurelius.tech.paymentrestservice.service;

import com.aurelius.tech.paymentrestservice.model.PaymentAcceptedResponse;
import com.aurelius.tech.paymentrestservice.model.PaymentCreateRequest;
import com.aurelius.tech.paymentrestservice.model.PaymentRequest;
import com.aurelius.tech.paymentrestservice.model.PaymentStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {


    private final KafkaTemplate<String, PaymentRequest> kafkaTemplate;

    @Value("${topics.payment-requested:payments-topic}")
    private String paymentRequestedTopic;

    public PaymentCommandServiceImpl(KafkaTemplate<String, PaymentRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public PaymentAcceptedResponse acceptPayment(PaymentCreateRequest request, String correlationId) {

        // 1) Create paymentId
        String paymentId = "PAY-" + UUID.randomUUID();


        // 3) Publish Kafka message
        PaymentRequest msg = PaymentRequest.builder()
                .eventId(UUID.randomUUID().toString())
                .eventType("PAYMENT_REQUESTED")
                .paymentId(paymentId)
                .orderId(request.orderId())
                .amount(request.amount())
                .currency(request.currency())
                .paymentMethod(request.paymentMethod())
                .customerId(request.customerId())
                .requestedAt(Instant.now())
                .correlationId(correlationId)
                .retryCount(0)
                .build();

        // Key = paymentId to preserve ordering per payment
        kafkaTemplate.send(paymentRequestedTopic, paymentId, msg).whenComplete((result, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
            } else {
                System.out.println("Message sent to partition " +
                        result.getRecordMetadata().partition());
            }
        });

        return new PaymentAcceptedResponse(paymentId, PaymentStatus.PENDING.name(), correlationId);
    }
}

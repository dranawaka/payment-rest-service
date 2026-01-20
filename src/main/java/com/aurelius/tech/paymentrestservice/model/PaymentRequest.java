package com.aurelius.tech.paymentrestservice.model;

import java.math.BigDecimal;
import java.time.Instant;

public class PaymentRequest {

    private String eventId;
    private String eventType;
    private String paymentId;
    private String orderId;
    private String customerId;
    private BigDecimal amount;
    private String currency;
    private String paymentMethod;
    private Instant requestedAt;
    private String correlationId;
    private int retryCount;



    private PaymentRequest(Builder builder) {
        this.eventId = builder.eventId;
        this.eventType = builder.eventType;
        this.paymentId = builder.paymentId;
        this.orderId = builder.orderId;
        this.customerId = builder.customerId;
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.paymentMethod = builder.paymentMethod;
        this.requestedAt = builder.requestedAt;
        this.correlationId = builder.correlationId;
        this.retryCount = builder.retryCount;
    }

    // ---------- Getters ----------

    public String getEventId() {
        return eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Instant getRequestedAt() {
        return requestedAt;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public int getRetryCount() {
        return retryCount;
    }

    // ---------- Builder ----------

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String eventId;
        private String eventType;
        private String paymentId;
        private String orderId;
        private String customerId;
        private BigDecimal amount;
        private String currency;
        private String paymentMethod;
        private Instant requestedAt;
        private String correlationId;
        private int retryCount;

        private Builder() {
        }

        public Builder eventId(String eventId) {
            this.eventId = eventId;
            return this;
        }

        public Builder eventType(String eventType) {
            this.eventType = eventType;
            return this;
        }

        public Builder paymentId(String paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder customerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder paymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder requestedAt(Instant requestedAt) {
            this.requestedAt = requestedAt;
            return this;
        }

        public Builder correlationId(String correlationId) {
            this.correlationId = correlationId;
            return this;
        }

        public Builder retryCount(int retryCount) {
            this.retryCount = retryCount;
            return this;
        }

        public PaymentRequest build() {
            return new PaymentRequest(this);
        }
    }
}

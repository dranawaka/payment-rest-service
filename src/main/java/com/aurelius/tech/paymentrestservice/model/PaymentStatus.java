package com.aurelius.tech.paymentrestservice.model;

public enum PaymentStatus {

    PENDING,        // Payment accepted, processing not yet completed
    PROCESSING,     // Consumer has picked up the message
    SUCCESS,        // Payment completed successfully
    FAILED,         // Payment failed (business or gateway failure)
    CANCELLED       // Payment cancelled by user/system
}

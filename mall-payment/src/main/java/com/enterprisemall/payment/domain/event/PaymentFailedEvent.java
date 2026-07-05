package com.enterprisemall.payment.domain.event;

import com.enterprisemall.common.event.DomainEvent;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 支付失败事件
 */
public class PaymentFailedEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    private final Long paymentId;
    private final String paymentNo;
    private final Long orderId;
    private final String orderNo;
    private final Long customerId;
    private final BigDecimal amount;
    private final String failureReason;
    private final LocalDateTime timestamp;

    public PaymentFailedEvent(Long paymentId,
                               String paymentNo,
                               Long orderId,
                               String orderNo,
                               Long customerId,
                               BigDecimal amount,
                               String failureReason) {
        super();
        this.paymentId = paymentId;
        this.paymentNo = paymentNo;
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.customerId = customerId;
        this.amount = amount;
        this.failureReason = failureReason;
        this.timestamp = LocalDateTime.now();
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

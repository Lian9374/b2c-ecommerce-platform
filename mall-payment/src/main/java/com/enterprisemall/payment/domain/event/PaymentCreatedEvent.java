package com.enterprisemall.payment.domain.event;

import com.enterprisemall.common.event.DomainEvent;
import com.enterprisemall.payment.domain.model.valueobject.PaymentChannel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 支付创建事件
 */
public class PaymentCreatedEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    private final Long paymentId;
    private final String paymentNo;
    private final Long orderId;
    private final String orderNo;
    private final Long customerId;
    private final BigDecimal amount;
    private final PaymentChannel channel;
    private final LocalDateTime timestamp;

    public PaymentCreatedEvent(Long paymentId,
                                String paymentNo,
                                Long orderId,
                                String orderNo,
                                Long customerId,
                                BigDecimal amount,
                                PaymentChannel channel) {
        super();
        this.paymentId = paymentId;
        this.paymentNo = paymentNo;
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.customerId = customerId;
        this.amount = amount;
        this.channel = channel;
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

    public PaymentChannel getChannel() {
        return channel;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

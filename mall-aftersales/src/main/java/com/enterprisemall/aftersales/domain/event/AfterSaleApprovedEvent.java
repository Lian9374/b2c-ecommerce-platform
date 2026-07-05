package com.enterprisemall.aftersales.domain.event;

import com.enterprisemall.common.event.DomainEvent;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Published when an after-sale application is approved by the seller/admin.
 */
public class AfterSaleApprovedEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    private final Long afterSaleOrderId;
    private final String afterSaleNo;
    private final Long orderId;
    private final Long customerId;
    private final BigDecimal refundAmount;
    private final LocalDateTime approvedAt;

    public AfterSaleApprovedEvent(Long afterSaleOrderId,
                                   String afterSaleNo,
                                   Long orderId,
                                   Long customerId,
                                   BigDecimal refundAmount) {
        super();
        this.afterSaleOrderId = afterSaleOrderId;
        this.afterSaleNo = afterSaleNo;
        this.orderId = orderId;
        this.customerId = customerId;
        this.refundAmount = refundAmount;
        this.approvedAt = LocalDateTime.now();
    }

    public Long getAfterSaleOrderId() {
        return afterSaleOrderId;
    }

    public String getAfterSaleNo() {
        return afterSaleNo;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public LocalDateTime getApprovedAt() {
        return approvedAt;
    }
}

package com.enterprisemall.order.domain.event;

import com.enterprisemall.common.event.DomainEvent;

import java.time.LocalDateTime;

/**
 * 订单已发货领域事件
 */
public class OrderDeliveredEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private final Long orderId;

    /** 订单编号 */
    private final String orderNo;

    /** 客户ID */
    private final Long customerId;

    /** 物流单号 */
    private final String trackingNo;

    /** 物流公司 */
    private final String logisticsCompany;

    /** 发货时间 */
    private final LocalDateTime deliveredAt;

    public OrderDeliveredEvent(Long orderId, String orderNo, Long customerId,
                                String trackingNo, String logisticsCompany) {
        super();
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.customerId = customerId;
        this.trackingNo = trackingNo;
        this.logisticsCompany = logisticsCompany;
        this.deliveredAt = LocalDateTime.now();
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

    public String getTrackingNo() {
        return trackingNo;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }
}

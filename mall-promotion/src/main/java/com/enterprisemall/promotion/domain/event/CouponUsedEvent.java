package com.enterprisemall.promotion.domain.event;

import com.enterprisemall.common.event.DomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 优惠券使用领域事件
 */
@Getter
public class CouponUsedEvent extends DomainEvent {

    private final Long couponId;
    private final Long customerId;
    private final Long orderId;
    private final LocalDateTime usedAt;

    public CouponUsedEvent(Long couponId, Long customerId, Long orderId) {
        super();
        this.couponId = couponId;
        this.customerId = customerId;
        this.orderId = orderId;
        this.usedAt = LocalDateTime.now();
    }
}

package com.enterprisemall.promotion.domain.event;

import com.enterprisemall.common.event.DomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 优惠券发放领域事件
 */
@Getter
public class CouponIssuedEvent extends DomainEvent {

    private final Long couponId;
    private final Long customerId;
    private final LocalDateTime issuedAt;

    public CouponIssuedEvent(Long couponId, Long customerId) {
        super();
        this.couponId = couponId;
        this.customerId = customerId;
        this.issuedAt = LocalDateTime.now();
    }
}

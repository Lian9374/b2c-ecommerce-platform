package com.enterprisemall.cart.domain.event;

import com.enterprisemall.common.event.DomainEvent;

/**
 * 购物车合并事件.
 * 当用户登录后，将匿名购物车与用户购物车合并时发布.
 */
public class CartMergedEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    /** 顾客 ID */
    private final Long customerId;

    /** 匿名购物车 ID（合并后可能被清除） */
    private final Long anonymousCartId;

    /** 用户购物车 ID（合并后的目标购物车） */
    private final Long userCartId;

    public CartMergedEvent(Long customerId,
                           Long anonymousCartId,
                           Long userCartId) {
        super();
        this.customerId = customerId;
        this.anonymousCartId = anonymousCartId;
        this.userCartId = userCartId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getAnonymousCartId() {
        return anonymousCartId;
    }

    public Long getUserCartId() {
        return userCartId;
    }
}

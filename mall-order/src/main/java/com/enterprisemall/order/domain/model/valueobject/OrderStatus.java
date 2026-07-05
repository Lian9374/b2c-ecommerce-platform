package com.enterprisemall.order.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * 订单状态值对象（枚举）
 */
@Getter
@AllArgsConstructor
public enum OrderStatus implements Serializable {

    PENDING_PAY(0, "待支付"),
    PAID(1, "已支付"),
    DELIVERED(2, "已发货"),
    RECEIVED(3, "已收货"),
    COMPLETED(4, "已完成"),
    CANCELLED(5, "已取消");

    private final int code;
    private final String description;

    /**
     * 根据code获取枚举
     */
    public static OrderStatus of(int code) {
        for (OrderStatus status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("未知的订单状态code: " + code);
    }

    /**
     * 判断是否为终态
     */
    public boolean isFinal() {
        return this == COMPLETED || this == CANCELLED;
    }

    /**
     * 判断是否可以支付
     */
    public boolean canPay() {
        return this == PENDING_PAY;
    }

    /**
     * 判断是否可以发货
     */
    public boolean canDeliver() {
        return this == PAID;
    }

    /**
     * 判断是否可以确认收货
     */
    public boolean canReceive() {
        return this == DELIVERED;
    }

    /**
     * 判断是否可以取消
     */
    public boolean canCancel() {
        return this == PENDING_PAY || this == PAID;
    }
}

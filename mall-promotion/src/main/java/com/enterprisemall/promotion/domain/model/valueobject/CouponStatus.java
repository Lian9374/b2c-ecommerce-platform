package com.enterprisemall.promotion.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 优惠券状态值对象
 */
@Data
@AllArgsConstructor
public enum CouponStatus implements Serializable {

    ACTIVE(1, "可用"),
    USED(2, "已使用"),
    EXPIRED(3, "已过期"),
    DISABLED(4, "已禁用");

    private final int code;
    private final String description;
}

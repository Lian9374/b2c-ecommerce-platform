package com.enterprisemall.promotion.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 优惠券类型值对象
 */
@Data
@AllArgsConstructor
public enum CouponType implements Serializable {

    FULL_REDUCTION(1, "满减"),
    DISCOUNT(2, "折扣"),
    DIRECT_REDUCTION(3, "直减");

    private final int code;
    private final String description;
}

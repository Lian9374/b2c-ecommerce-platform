package com.enterprisemall.promotion.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 促销活动类型值对象
 */
@Data
@AllArgsConstructor
public enum PromotionType implements Serializable {

    FULL_DISCOUNT(1, "满减"),
    DIRECT_DISCOUNT(2, "直降"),
    FLASH_SALE(3, "秒杀");

    private final int code;
    private final String description;
}

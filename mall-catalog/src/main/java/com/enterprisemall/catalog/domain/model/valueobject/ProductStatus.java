package com.enterprisemall.catalog.domain.model.valueobject;

import lombok.Getter;

/**
 * 商品状态枚举（值对象）
 */
@Getter
public enum ProductStatus {

    DRAFT(0, "草稿"),
    SHELVED(1, "已上架"),
    OFF_SHELVED(2, "已下架");

    private final int code;
    private final String desc;

    ProductStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据code获取枚举值
     */
    public static ProductStatus of(int code) {
        for (ProductStatus status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("无效的商品状态编码: " + code);
    }
}

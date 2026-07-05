package com.enterprisemall.catalog.domain.model.valueobject;

import lombok.Getter;

/**
 * 图片类型枚举（值对象）
 */
@Getter
public enum ImageType {

    MAIN(1, "主图"),
    DETAIL(2, "详情图"),
    SPEC(3, "规格图");

    private final int code;
    private final String desc;

    ImageType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据code获取枚举值
     */
    public static ImageType of(int code) {
        for (ImageType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("无效的图片类型编码: " + code);
    }
}

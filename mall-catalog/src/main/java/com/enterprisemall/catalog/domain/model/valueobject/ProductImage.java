package com.enterprisemall.catalog.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品图片值对象
 */
@Data
@AllArgsConstructor
public class ProductImage implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 图片ID */
    private Long id;

    /** 图片URL */
    private String url;

    /** 图片类型 */
    private ImageType type;

    /** 排序 */
    private Integer sort;

    /**
     * 创建商品图片的工厂方法
     */
    public static ProductImage create(Long id, String url, ImageType type, Integer sort) {
        return new ProductImage(id, url, type, sort != null ? sort : 0);
    }
}

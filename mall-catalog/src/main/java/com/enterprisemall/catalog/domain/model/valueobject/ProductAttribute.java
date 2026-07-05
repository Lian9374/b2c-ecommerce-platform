package com.enterprisemall.catalog.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品属性值对象
 */
@Data
@AllArgsConstructor
public class ProductAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 属性ID */
    private Long id;

    /** 属性名称 */
    private String name;

    /** 属性值 */
    private String value;

    /** 属性类型 */
    private AttributeType type;

    /**
     * 创建商品属性的工厂方法
     */
    public static ProductAttribute create(Long id, String name, String value, AttributeType type) {
        return new ProductAttribute(id, name, value, type);
    }
}

package com.enterprisemall.catalog.domain.model.entity;

import com.enterprisemall.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * SKU 实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Sku extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 所属商品ID */
    private Long productId;

    /** SKU编码 */
    private String skuCode;

    /** 规格值描述（如：颜色:红色, 尺寸:XL） */
    private String specValues;

    /** 成本价格 */
    private BigDecimal costPrice;

    /** 销售价格 */
    private BigDecimal salePrice;

    /** 条形码 */
    private String barcode;

    /** 重量（克） */
    private Integer weight;

    /**
     * 创建SKU的工厂方法
     */
    public static Sku create(Long productId, String skuCode, String specValues,
                              BigDecimal costPrice, BigDecimal salePrice, String barcode, Integer weight) {
        Sku sku = new Sku();
        sku.setProductId(productId);
        sku.setSkuCode(skuCode);
        sku.setSpecValues(specValues);
        sku.setCostPrice(costPrice);
        sku.setSalePrice(salePrice);
        sku.setBarcode(barcode);
        sku.setWeight(weight != null ? weight : 0);
        return sku;
    }
}

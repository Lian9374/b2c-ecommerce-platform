package com.enterprisemall.pricing.domain.model.entity;

import com.enterprisemall.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 价格聚合根 — 管理商品在不同维度的定价信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Price extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 关联的 SKU ID */
    private Long skuId;

    /** 原价（市场参考价） */
    private BigDecimal originalPrice;

    /** 售价（当前销售价格） */
    private BigDecimal sellingPrice;

    /** 会员价 */
    private BigDecimal memberPrice;

    /** 促销价 */
    private BigDecimal promotionPrice;

    /**
     * 静态工厂方法 — 创建价格实体
     */
    public static Price create(Long skuId,
                               BigDecimal originalPrice,
                               BigDecimal sellingPrice,
                               BigDecimal memberPrice,
                               BigDecimal promotionPrice) {
        Price price = new Price();
        price.setSkuId(skuId);
        price.setOriginalPrice(originalPrice);
        price.setSellingPrice(sellingPrice);
        price.setMemberPrice(memberPrice);
        price.setPromotionPrice(promotionPrice);
        LocalDateTime now = LocalDateTime.now();
        price.setCreatedAt(now);
        price.setUpdatedAt(now);
        return price;
    }
}

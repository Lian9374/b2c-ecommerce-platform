package com.enterprisemall.pricing.domain.event;

import com.enterprisemall.common.event.DomainEvent;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 价格变更领域事件 — 当价格发生变更时发布
 */
@Getter
public class PriceChangedEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    /** 价格 ID */
    private final Long priceId;

    /** 关联的 SKU ID */
    private final Long skuId;

    /** 变更前的售价 */
    private final BigDecimal oldSellingPrice;

    /** 变更后的售价 */
    private final BigDecimal newSellingPrice;

    /** 变更前的促销价 */
    private final BigDecimal oldPromotionPrice;

    /** 变更后的促销价 */
    private final BigDecimal newPromotionPrice;

    /** 变更发生时间 */
    private final LocalDateTime changedAt;

    public PriceChangedEvent(Long priceId,
                              Long skuId,
                              BigDecimal oldSellingPrice,
                              BigDecimal newSellingPrice,
                              BigDecimal oldPromotionPrice,
                              BigDecimal newPromotionPrice) {
        super();
        this.priceId = priceId;
        this.skuId = skuId;
        this.oldSellingPrice = oldSellingPrice;
        this.newSellingPrice = newSellingPrice;
        this.oldPromotionPrice = oldPromotionPrice;
        this.newPromotionPrice = newPromotionPrice;
        this.changedAt = LocalDateTime.now();
    }
}

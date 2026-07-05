package com.enterprisemall.pricing.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 价格快照值对象 — 某一时刻的价格断面，用于询价结果和历史追溯
 */
@Data
@AllArgsConstructor
public class PriceSnapshot implements Serializable {

    private static final long serialVersionUID = 1L;

    /** SKU ID */
    private Long skuId;

    /** 原价 */
    private BigDecimal originalPrice;

    /** 售价 */
    private BigDecimal sellingPrice;

    /** 最终成交价（扣除所有优惠后） */
    private BigDecimal finalPrice;

    /** 快照时间 */
    private LocalDateTime capturedAt;
}

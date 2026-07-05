package com.enterprisemall.pricing.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 促销规则值对象 — 描述一条可应用于价格的优惠规则
 */
@Data
@AllArgsConstructor
public class PromotionRule implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 规则类型：DISCOUNT / FIXED_AMOUNT_OFF */
    private String ruleType;

    /** 优惠值（折扣为 0-1 系数，立减为金额） */
    private BigDecimal discountValue;

    /** 优先级，数字越小越先执行 */
    private Integer priority;

    /** 规则名称 */
    private String ruleName;
}

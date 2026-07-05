package com.enterprisemall.promotion.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 促销规则值对象
 */
@Data
@AllArgsConstructor
public class PromotionRule implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private PromotionType type;
    private BigDecimal threshold;
    private BigDecimal discountValue;
    private String applicableScope;
}

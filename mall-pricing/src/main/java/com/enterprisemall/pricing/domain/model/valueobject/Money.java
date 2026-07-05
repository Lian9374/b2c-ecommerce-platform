package com.enterprisemall.pricing.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 金额值对象 — 封装金额与币种
 */
@Data
@AllArgsConstructor
public class Money implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 金额 */
    private BigDecimal amount;

    /** 币种，如 CNY、USD */
    private String currency;

    /**
     * 便捷构造 — 默认人民币
     */
    public static Money of(BigDecimal amount) {
        return new Money(amount, "CNY");
    }

    /**
     * 便捷构造 — 指定币种
     */
    public static Money of(BigDecimal amount, String currency) {
        return new Money(amount, currency);
    }
}

package com.enterprisemall.pricing.domain.model.valueobject;

import com.enterprisemall.pricing.domain.model.entity.Price;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

/**
 * 价格计算器值对象 — 根据定价与促销规则计算最终价格快照
 */
@Data
@AllArgsConstructor
public class PriceCalculator implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 计算最终价格快照。
     * 规则按优先级升序依次叠加到售价上，结果不低于 0。
     *
     * @param price 商品定价
     * @param rules 适用的促销规则列表（可为 null 或空，表示无促销）
     * @return 价格快照
     */
    public PriceSnapshot calculate(Price price, List<PromotionRule> rules) {
        BigDecimal finalPrice = price.getSellingPrice();

        if (rules != null && !rules.isEmpty()) {
            rules.stream()
                    .sorted(Comparator.comparingInt(r -> r.getPriority() != null ? r.getPriority() : Integer.MAX_VALUE))
                    .forEach(rule -> finalPrice = applyRule(finalPrice, rule));
        }

        if (finalPrice.compareTo(BigDecimal.ZERO) < 0) {
            finalPrice = BigDecimal.ZERO;
        }

        return new PriceSnapshot(
                price.getSkuId(),
                price.getOriginalPrice(),
                price.getSellingPrice(),
                finalPrice.setScale(2, RoundingMode.HALF_UP),
                LocalDateTime.now()
        );
    }

    private BigDecimal applyRule(BigDecimal price, PromotionRule rule) {
        if (rule.getDiscountValue() == null) {
            return price;
        }
        if ("DISCOUNT".equalsIgnoreCase(rule.getRuleType())) {
            return price.multiply(rule.getDiscountValue());
        }
        if ("FIXED_AMOUNT_OFF".equalsIgnoreCase(rule.getRuleType())) {
            return price.subtract(rule.getDiscountValue());
        }
        return price;
    }
}

package com.enterprisemall.order.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单价格快照值对象 — 下单时锁定价格信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPriceSnapshot implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 商品总金额 */
    private BigDecimal totalAmount;

    /** 优惠金额 */
    private BigDecimal discountAmount;

    /** 运费 */
    private BigDecimal shippingFee;

    /** 实付金额 */
    private BigDecimal payAmount;

    /**
     * 创建价格快照
     */
    public static OrderPriceSnapshot of(BigDecimal totalAmount, BigDecimal discountAmount,
                                         BigDecimal shippingFee) {
        BigDecimal payAmount = totalAmount.subtract(discountAmount).add(shippingFee);
        return new OrderPriceSnapshot(totalAmount, discountAmount, shippingFee, payAmount);
    }

    /**
     * 计算实付金额
     */
    public BigDecimal calcPayAmount() {
        return totalAmount.subtract(discountAmount).add(shippingFee);
    }
}

package com.enterprisemall.order.domain.model.entity;

import com.enterprisemall.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单项实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderItem extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 所属订单ID */
    private Long orderId;

    /** 商品ID */
    private Long productId;

    /** 商品名称 */
    private String productName;

    /** SKU ID */
    private Long skuId;

    /** SKU规格描述 */
    private String skuSpec;

    /** 商品图片 */
    private String productImage;

    /** 单价 */
    private BigDecimal price;

    /** 数量 */
    private Integer quantity;

    /** 小计 */
    private BigDecimal subtotal;

    /**
     * 静态工厂方法 — 创建订单项
     */
    public static OrderItem create(Long orderId, Long productId, String productName,
                                    Long skuId, String skuSpec, String productImage,
                                    BigDecimal price, Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("商品数量必须大于0");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("商品单价必须大于0");
        }

        OrderItem item = new OrderItem();
        item.setOrderId(orderId);
        item.setProductId(productId);
        item.setProductName(productName);
        item.setSkuId(skuId);
        item.setSkuSpec(skuSpec);
        item.setProductImage(productImage);
        item.setPrice(price);
        item.setQuantity(quantity);
        item.setSubtotal(price.multiply(BigDecimal.valueOf(quantity)));
        return item;
    }

    /**
     * 修改数量并重新计算小计
     */
    public void changeQuantity(Integer newQuantity) {
        if (newQuantity == null || newQuantity <= 0) {
            throw new IllegalArgumentException("商品数量必须大于0");
        }
        this.quantity = newQuantity;
        this.subtotal = this.price.multiply(BigDecimal.valueOf(newQuantity));
    }
}

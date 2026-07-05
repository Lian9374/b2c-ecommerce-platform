package com.enterprisemall.cart.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 购物车项实体.
 * 表示购物车中的一件商品条目，与 SKU 一一对应.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 购物车项主键 ID */
    private Long id;

    /** 所属购物车 ID */
    private Long cartId;

    /** SKU ID */
    private Long skuId;

    /** 商品 SPU ID */
    private Long productId;

    /** 商品名称（快照） */
    private String productName;

    /** 规格描述（快照） */
    private String specDescription;

    /** 商品主图 URL（快照） */
    private String productImage;

    /** 加入购物车时的单价 */
    private BigDecimal price;

    /** 购买数量 */
    private Integer quantity;

    /** 是否被选中（用于下单） */
    private Boolean selected;

    /** 创建时间 */
    private LocalDateTime createdAt;

    /** 更新时间 */
    private LocalDateTime updatedAt;

    /**
     * 静态工厂方法 — 创建一个新的购物车项.
     *
     * @param cartId          购物车 ID
     * @param skuId           SKU ID
     * @param productId       商品 SPU ID
     * @param productName     商品名称
     * @param specDescription 规格描述
     * @param productImage    商品主图
     * @param price           单价
     * @param quantity        购买数量
     * @return 新建的购物车项实例（默认选中）
     */
    public static CartItem create(Long cartId,
                                  Long skuId,
                                  Long productId,
                                  String productName,
                                  String specDescription,
                                  String productImage,
                                  BigDecimal price,
                                  Integer quantity) {
        CartItem item = new CartItem();
        item.setCartId(cartId);
        item.setSkuId(skuId);
        item.setProductId(productId);
        item.setProductName(productName);
        item.setSpecDescription(specDescription);
        item.setProductImage(productImage);
        item.setPrice(price);
        item.setQuantity(quantity);
        item.setSelected(true);
        item.setCreatedAt(LocalDateTime.now());
        item.setUpdatedAt(LocalDateTime.now());
        return item;
    }
}

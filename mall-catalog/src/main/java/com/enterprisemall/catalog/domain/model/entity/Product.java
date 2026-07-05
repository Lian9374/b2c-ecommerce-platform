package com.enterprisemall.catalog.domain.model.entity;

import com.enterprisemall.catalog.domain.model.valueobject.ProductStatus;
import com.enterprisemall.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 商品聚合根
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 商品名称 */
    private String name;

    /** 商品副标题 */
    private String subtitle;

    /** 所属分类ID */
    private Long categoryId;

    /** 所属品牌ID */
    private Long brandId;

    /** 商品状态 */
    private ProductStatus status;

    /** 商品描述 */
    private String description;

    /**
     * 创建商品的工厂方法
     */
    public static Product create(String name, String subtitle, Long categoryId, Long brandId, String description) {
        Product product = new Product();
        product.setName(name);
        product.setSubtitle(subtitle);
        product.setCategoryId(categoryId);
        product.setBrandId(brandId);
        product.setStatus(ProductStatus.DRAFT);
        product.setDescription(description);
        return product;
    }

    /**
     * 上架
     */
    public void shelve() {
        if (this.status == ProductStatus.SHELVED) {
            throw new IllegalStateException("商品已处于上架状态");
        }
        this.status = ProductStatus.SHELVED;
    }

    /**
     * 下架
     */
    public void offShelve() {
        if (this.status == ProductStatus.OFF_SHELVED) {
            throw new IllegalStateException("商品已处于下架状态");
        }
        this.status = ProductStatus.OFF_SHELVED;
    }
}

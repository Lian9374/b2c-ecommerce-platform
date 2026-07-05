package com.enterprisemall.catalog.domain.repository;

import com.enterprisemall.catalog.domain.model.entity.Product;
import com.enterprisemall.catalog.domain.model.valueobject.ProductStatus;
import com.enterprisemall.common.base.BaseRepository;

import java.util.List;

/**
 * 商品仓储接口
 */
public interface ProductRepository extends BaseRepository<Product> {

    /**
     * 根据分类ID查询商品列表
     */
    List<Product> findByCategoryId(Long categoryId);

    /**
     * 根据品牌ID查询商品列表
     */
    List<Product> findByBrandId(Long brandId);

    /**
     * 根据状态查询商品列表
     */
    List<Product> findByStatus(ProductStatus status);

    /**
     * 根据名称模糊查询
     */
    List<Product> findByNameLike(String keyword);

    /**
     * 根据分类ID和状态查询商品
     */
    List<Product> findByCategoryIdAndStatus(Long categoryId, ProductStatus status);

    /**
     * 根据品牌ID和状态查询商品
     */
    List<Product> findByBrandIdAndStatus(Long brandId, ProductStatus status);
}

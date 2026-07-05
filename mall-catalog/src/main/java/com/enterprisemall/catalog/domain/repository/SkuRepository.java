package com.enterprisemall.catalog.domain.repository;

import com.enterprisemall.catalog.domain.model.entity.Sku;
import com.enterprisemall.common.base.BaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * SKU 仓储接口
 */
public interface SkuRepository extends BaseRepository<Sku> {

    /**
     * 根据商品ID查询SKU列表
     */
    List<Sku> findByProductId(Long productId);

    /**
     * 根据SKU编码查询
     */
    Optional<Sku> findBySkuCode(String skuCode);

    /**
     * 根据条形码查询
     */
    Optional<Sku> findByBarcode(String barcode);
}

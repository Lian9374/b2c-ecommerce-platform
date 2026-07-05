package com.enterprisemall.inventory.domain.repository;

import com.enterprisemall.common.base.BaseRepository;
import com.enterprisemall.inventory.domain.model.entity.Stock;

import java.util.List;
import java.util.Optional;

/**
 * 库存仓储接口
 */
public interface StockRepository extends BaseRepository<Stock> {

    /**
     * 根据SKU ID查询库存
     */
    Optional<Stock> findBySkuId(Long skuId);

    /**
     * 根据SKU编码查询库存
     */
    Optional<Stock> findBySkuCode(String skuCode);

    /**
     * 根据SKU ID列表批量查询库存
     */
    List<Stock> findBySkuIdIn(List<Long> skuIds);

    /**
     * 查询可用库存低于阈值的记录（用于库存预警）
     */
    List<Stock> findByAvailableQuantityLessThanEqual(Integer threshold);

    /**
     * 乐观锁更新 — 根据版本号更新库存
     */
    int updateWithVersion(Stock stock);
}

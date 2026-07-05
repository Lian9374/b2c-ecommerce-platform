package com.enterprisemall.pricing.domain.repository;

import com.enterprisemall.common.domain.BaseRepository;
import com.enterprisemall.pricing.domain.model.entity.Price;

import java.util.List;
import java.util.Optional;

/**
 * 价格仓储接口 — 声明价格聚合的持久化契约
 */
public interface PriceRepository extends BaseRepository<Price> {

    /**
     * 按 SKU ID 查询价格
     */
    Optional<Price> findBySkuId(Long skuId);

    /**
     * 批量按 SKU ID 列表查询价格
     */
    List<Price> findBySkuIdIn(List<Long> skuIds);
}

package com.enterprisemall.catalog.domain.repository;

import com.enterprisemall.catalog.domain.model.entity.Brand;
import com.enterprisemall.common.base.BaseRepository;
import com.enterprisemall.common.enums.CommonStatus;

import java.util.List;

/**
 * 品牌仓储接口
 */
public interface BrandRepository extends BaseRepository<Brand> {

    /**
     * 根据品牌名称查询
     */
    List<Brand> findByName(String name);

    /**
     * 根据状态查询品牌列表
     */
    List<Brand> findByStatus(CommonStatus status);

    /**
     * 根据名称模糊查询
     */
    List<Brand> findByNameLike(String keyword);
}

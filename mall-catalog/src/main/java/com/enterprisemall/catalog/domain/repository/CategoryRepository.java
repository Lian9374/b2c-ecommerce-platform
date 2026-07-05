package com.enterprisemall.catalog.domain.repository;

import com.enterprisemall.catalog.domain.model.entity.Category;
import com.enterprisemall.common.base.BaseRepository;
import com.enterprisemall.common.enums.CommonStatus;

import java.util.List;

/**
 * 商品分类仓储接口
 */
public interface CategoryRepository extends BaseRepository<Category> {

    /**
     * 根据分类名称查询
     */
    List<Category> findByName(String name);

    /**
     * 根据父级ID查询子分类
     */
    List<Category> findByParentId(Long parentId);

    /**
     * 根据状态查询分类列表
     */
    List<Category> findByStatus(CommonStatus status);

    /**
     * 根据层级查询分类列表
     */
    List<Category> findByLevel(Integer level);

    /**
     * 根据父级ID和状态查询分类
     */
    List<Category> findByParentIdAndStatus(Long parentId, CommonStatus status);
}

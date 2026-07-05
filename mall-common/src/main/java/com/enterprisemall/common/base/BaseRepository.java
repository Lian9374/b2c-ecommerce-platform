package com.enterprisemall.common.base;

import java.util.List;
import java.util.Optional;

/**
 * 仓储基类接口 — 所有领域仓储接口继承此接口
 *
 * @param <T> 实体类型
 */
public interface BaseRepository<T> {

    /**
     * 根据ID查询
     */
    Optional<T> findById(Long id);

    /**
     * 查询所有
     */
    List<T> findAll();

    /**
     * 保存（新增或更新）
     */
    void save(T entity);

    /**
     * 根据ID删除
     */
    void deleteById(Long id);

    /**
     * 判断ID是否存在
     */
    boolean existsById(Long id);
}

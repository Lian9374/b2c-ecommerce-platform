package com.enterprisemall.auth.domain.repository;

import com.enterprisemall.auth.domain.model.entity.Permission;
import com.enterprisemall.auth.domain.model.valueobject.PermissionType;
import com.enterprisemall.common.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * 权限仓储接口
 */
public interface PermissionRepository extends BaseRepository<Permission> {

    Optional<Permission> findByCode(String code);

    List<Permission> findByParentId(Long parentId);

    List<Permission> findByType(PermissionType type);

    List<Permission> findByParentIdIsNull();
}

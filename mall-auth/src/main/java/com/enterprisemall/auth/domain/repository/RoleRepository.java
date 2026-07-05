package com.enterprisemall.auth.domain.repository;

import com.enterprisemall.auth.domain.model.entity.Role;
import com.enterprisemall.common.enums.CommonStatus;
import com.enterprisemall.common.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * 角色仓储接口
 */
public interface RoleRepository extends BaseRepository<Role> {

    Optional<Role> findByCode(String code);

    List<Role> findByStatus(CommonStatus status);

    boolean existsByCode(String code);
}

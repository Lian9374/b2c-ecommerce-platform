package com.enterprisemall.auth.domain.repository;

import com.enterprisemall.auth.domain.model.entity.Account;
import com.enterprisemall.auth.domain.model.valueobject.AccountStatus;
import com.enterprisemall.common.repository.BaseRepository;

import java.util.Optional;

/**
 * 账号仓储接口
 */
public interface AccountRepository extends BaseRepository<Account> {

    Optional<Account> findByUsername(String username);

    Optional<Account> findByUserId(Long userId);

    boolean existsByUsername(String username);
}

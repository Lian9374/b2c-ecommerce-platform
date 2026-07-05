package com.enterprisemall.audit.domain.repository;

import com.enterprisemall.audit.domain.model.entity.AuditLog;
import com.enterprisemall.common.base.BaseRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 审计日志仓储接口 — 声明审计日志的持久化操作.
 * 只定义方法签名，不提供实现（由基础设施层实现）.
 */
public interface AuditLogRepository extends BaseRepository<AuditLog> {

    /**
     * 根据用户 ID 查询审计日志列表.
     */
    List<AuditLog> findByUserId(Long userId);

    /**
     * 根据操作类型查询审计日志列表.
     */
    List<AuditLog> findByOperation(String operation);

    /**
     * 根据操作目标类型和目标 ID 查询审计日志列表.
     */
    List<AuditLog> findByTargetAndTargetId(String target, Long targetId);

    /**
     * 根据操作目标类型查询审计日志列表.
     */
    List<AuditLog> findByTarget(String target);

    /**
     * 根据用户名模糊查询审计日志列表.
     */
    List<AuditLog> findByUsername(String username);

    /**
     * 根据操作是否成功查询审计日志列表.
     */
    List<AuditLog> findBySuccess(Boolean success);

    /**
     * 根据时间范围查询审计日志列表.
     */
    List<AuditLog> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    /**
     * 根据用户 ID 和操作类型查询审计日志列表.
     */
    List<AuditLog> findByUserIdAndOperation(Long userId, String operation);

    /**
     * 根据目标类型和目标 ID 查询最新一条审计日志.
     */
    Optional<AuditLog> findTopByTargetAndTargetIdOrderByCreatedAtDesc(String target, Long targetId);
}

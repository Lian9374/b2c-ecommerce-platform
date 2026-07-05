package com.enterprisemall.audit.domain.model.entity;

import com.enterprisemall.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 审计日志实体 — 记录系统中所有关键操作.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AuditLog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 执行操作的用户 ID */
    private Long userId;

    /** 执行操作的用户名 */
    private String username;

    /** 操作类型（如 CREATE、UPDATE、DELETE、LOGIN、EXPORT） */
    private String operation;

    /** 操作目标（如 ORDER、PRODUCT、USER） */
    private String target;

    /** 操作目标的主键 ID */
    private Long targetId;

    /** 操作详情（JSON 格式存储变更内容） */
    private String detail;

    /** 请求来源 IP 地址 */
    private String ip;

    /** 请求 User-Agent */
    private String userAgent;

    /** 操作耗时（毫秒） */
    private Long durationMs;

    /** 操作是否成功 */
    private Boolean success;

    /** 失败时的错误信息 */
    private String errorMsg;

    /**
     * 静态工厂方法 — 创建审计日志实例.
     */
    public static AuditLog create(Long userId, String username, String operation,
                                  String target, Long targetId, String detail,
                                  String ip, String userAgent, Long durationMs,
                                  Boolean success, String errorMsg) {
        AuditLog log = new AuditLog();
        log.setUserId(userId);
        log.setUsername(username);
        log.setOperation(operation);
        log.setTarget(target);
        log.setTargetId(targetId);
        log.setDetail(detail);
        log.setIp(ip);
        log.setUserAgent(userAgent);
        log.setDurationMs(durationMs);
        log.setSuccess(success);
        log.setErrorMsg(errorMsg);
        log.setCreatedAt(LocalDateTime.now());
        log.setUpdatedAt(LocalDateTime.now());
        return log;
    }
}

package com.enterprisemall.audit.domain.event;

import com.enterprisemall.audit.domain.model.entity.AuditLog;
import com.enterprisemall.common.event.DomainEvent;

/**
 * 审计日志创建事件 — 当一条新的审计日志被创建时发布.
 */
public class AuditLogCreatedEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    /** 审计日志 ID */
    private final Long auditLogId;

    /** 操作类型 */
    private final String operation;

    /** 操作目标 */
    private final String target;

    /** 操作用户 ID */
    private final Long userId;

    public AuditLogCreatedEvent(AuditLog auditLog) {
        super();
        this.auditLogId = auditLog.getId();
        this.operation = auditLog.getOperation();
        this.target = auditLog.getTarget();
        this.userId = auditLog.getUserId();
    }

    public Long getAuditLogId() {
        return auditLogId;
    }

    public String getOperation() {
        return operation;
    }

    public String getTarget() {
        return target;
    }

    public Long getUserId() {
        return userId;
    }
}

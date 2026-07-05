package com.enterprisemall.audit.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 操作日志值对象 — 表示一次业务操作记录的摘要信息.
 */
@Data
@AllArgsConstructor
public class OperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 日志主键 ID */
    private Long id;

    /** 操作所属模块 */
    private String module;

    /** 操作动作 */
    private String action;

    /** 操作描述 */
    private String description;

    /** 操作人 */
    private String operator;

    /** 操作时间 */
    private LocalDateTime operateAt;

    /** 操作结果 */
    private String result;
}

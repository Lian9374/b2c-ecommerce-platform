package com.enterprisemall.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 统一错误码枚举
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 4xx — 客户端错误
    BAD_REQUEST(40000, "请求参数错误"),
    VALIDATION_ERROR(40001, "参数校验失败"),
    METHOD_NOT_ALLOWED(40002, "请求方法不支持"),

    UNAUTHORIZED(40100, "未认证"),
    TOKEN_EXPIRED(40101, "令牌已过期"),
    TOKEN_INVALID(40102, "令牌无效"),
    LOGIN_FAILED(40103, "用户名或密码错误"),
    ACCOUNT_DISABLED(40104, "账户已被禁用"),

    FORBIDDEN(40300, "无权限访问"),
    INSUFFICIENT_PERMISSION(40301, "权限不足"),

    NOT_FOUND(40400, "资源不存在"),
    USER_NOT_FOUND(40401, "用户不存在"),
    PRODUCT_NOT_FOUND(40402, "商品不存在"),

    CONFLICT(40900, "业务冲突"),
    DUPLICATE_EMAIL(40901, "邮箱已存在"),
    STOCK_INSUFFICIENT(40902, "库存不足"),
    ORDER_STATUS_INVALID(40903, "订单状态不允许此操作"),

    // 5xx — 服务端错误
    INTERNAL_ERROR(50000, "系统内部错误"),
    DATABASE_ERROR(50001, "数据库操作失败"),
    CACHE_ERROR(50002, "缓存操作失败"),
    MQ_ERROR(50003, "消息队列操作失败");

    private final int code;
    private final String message;
}

package com.enterprisemall.common.constant;

/**
 * 系统常量
 */
public final class SystemConstants {

    private SystemConstants() {}

    /** 应用名称 */
    public static final String APP_NAME = "EnterpriseMall";

    /** API 版本前缀 */
    public static final String API_PREFIX = "/api/v1";

    /** 默认分页大小 */
    public static final int DEFAULT_PAGE_SIZE = 20;

    /** 最大分页大小 */
    public static final int MAX_PAGE_SIZE = 100;

    /** 订单超时时间（分钟） */
    public static final int ORDER_TIMEOUT_MINUTES = 30;

    /** 收货地址最大数量 */
    public static final int MAX_ADDRESS_COUNT = 20;

    /** 验证码过期时间（分钟） */
    public static final int VERIFICATION_CODE_EXPIRE_MINUTES = 5;
}

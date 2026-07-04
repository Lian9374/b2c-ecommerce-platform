package com.enterprisemall.common.constant;

/**
 * Redis Key 常量
 */
public final class RedisConstants {

    private RedisConstants() {}

    /** 验证码前缀 */
    public static final String VERIFICATION_CODE_PREFIX = "mall:vc:";

    /** JWT 黑名单前缀 */
    public static final String TOKEN_BLACKLIST_PREFIX = "mall:token:black:";

    /** 商品缓存前缀 */
    public static final String PRODUCT_CACHE_PREFIX = "mall:product:";

    /** 分类缓存 Key */
    public static final String CATEGORY_CACHE_KEY = "mall:categories";

    /** 用户信息缓存前缀 */
    public static final String USER_CACHE_PREFIX = "mall:user:";

    /** 库存缓存前缀 */
    public static final String INVENTORY_CACHE_PREFIX = "mall:inventory:";

    /** 分布式锁前缀 */
    public static final String LOCK_PREFIX = "mall:lock:";
}

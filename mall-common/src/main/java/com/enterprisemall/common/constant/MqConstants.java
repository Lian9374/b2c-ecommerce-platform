package com.enterprisemall.common.constant;

/**
 * RabbitMQ 常量
 */
public final class MqConstants {

    private MqConstants() {}

    /** 订单交换机 */
    public static final String ORDER_EXCHANGE = "ex.order";

    /** 订单延迟交换机 */
    public static final String ORDER_DELAYED_EXCHANGE = "ex.order.delayed";

    /** 订单超时取消队列 */
    public static final String ORDER_TIMEOUT_QUEUE = "q.order.timeout";

    /** 订单创建队列 */
    public static final String ORDER_CREATED_QUEUE = "q.order.created";

    /** 支付交换机 */
    public static final String PAYMENT_EXCHANGE = "ex.payment";

    /** 支付成功队列 */
    public static final String PAYMENT_SUCCESS_QUEUE = "q.payment.success";

    /** 通知交换机 */
    public static final String NOTIFICATION_EXCHANGE = "ex.notification";

    /** 通知队列 */
    public static final String NOTIFICATION_QUEUE = "q.notification.send";

    /** 审计交换机 */
    public static final String AUDIT_EXCHANGE = "ex.audit";

    /** 审计队列 */
    public static final String AUDIT_QUEUE = "q.audit.log";
}

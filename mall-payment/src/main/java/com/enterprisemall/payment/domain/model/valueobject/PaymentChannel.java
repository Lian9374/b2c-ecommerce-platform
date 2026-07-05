package com.enterprisemall.payment.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * 支付渠道值对象
 */
@Getter
@AllArgsConstructor
public enum PaymentChannel implements Serializable {

    WECHAT("微信支付"),
    ALIPAY("支付宝"),
    BANK_CARD("银行卡");

    private final String description;
}

package com.enterprisemall.order.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 订单地址快照值对象 — 下单时锁定收货地址
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderAddressSnapshot implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 收货人姓名 */
    private String receiverName;

    /** 收货人电话 */
    private String receiverPhone;

    /** 省份 */
    private String province;

    /** 城市 */
    private String city;

    /** 区/县 */
    private String district;

    /** 详细地址 */
    private String detailAddress;

    /**
     * 获取完整地址
     */
    public String getFullAddress() {
        return (province != null ? province : "")
                + (city != null ? city : "")
                + (district != null ? district : "")
                + (detailAddress != null ? detailAddress : "");
    }
}

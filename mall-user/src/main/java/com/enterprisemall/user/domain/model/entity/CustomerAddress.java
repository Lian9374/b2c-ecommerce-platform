package com.enterprisemall.user.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 客户地址实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 地址ID */
    private Long id;

    /** 客户ID */
    private Long customerId;

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

    /** 是否默认地址 */
    private Boolean isDefault;

    /** 创建时间 */
    private LocalDateTime createdAt;

    /** 更新时间 */
    private LocalDateTime updatedAt;

    /**
     * 静态工厂方法 — 创建新地址
     */
    public static CustomerAddress create(Long customerId, String receiverName, String receiverPhone,
                                          String province, String city, String district, String detailAddress) {
        CustomerAddress address = new CustomerAddress();
        address.setCustomerId(customerId);
        address.setReceiverName(receiverName);
        address.setReceiverPhone(receiverPhone);
        address.setProvince(province);
        address.setCity(city);
        address.setDistrict(district);
        address.setDetailAddress(detailAddress);
        address.setIsDefault(false);
        address.setCreatedAt(LocalDateTime.now());
        address.setUpdatedAt(LocalDateTime.now());
        return address;
    }

    /**
     * 设置为默认地址
     */
    public void markAsDefault() {
        this.isDefault = true;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 取消默认
     */
    public void unmarkDefault() {
        this.isDefault = false;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 更新地址信息
     */
    public void update(String receiverName, String receiverPhone, String province,
                       String city, String district, String detailAddress) {
        if (receiverName != null) {
            this.receiverName = receiverName;
        }
        if (receiverPhone != null) {
            this.receiverPhone = receiverPhone;
        }
        if (province != null) {
            this.province = province;
        }
        if (city != null) {
            this.city = city;
        }
        if (district != null) {
            this.district = district;
        }
        if (detailAddress != null) {
            this.detailAddress = detailAddress;
        }
        this.updatedAt = LocalDateTime.now();
    }
}

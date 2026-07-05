package com.enterprisemall.user.domain.model.entity;

import com.enterprisemall.user.domain.model.valueobject.CustomerStatus;
import com.enterprisemall.user.domain.model.valueobject.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 客户实体（聚合根）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 客户ID */
    private Long id;

    /** 昵称 */
    private String nickname;

    /** 邮箱 */
    private String email;

    /** 手机号 */
    private String phone;

    /** 头像地址 */
    private String avatar;

    /** 性别 */
    private Gender gender;

    /** 客户状态 */
    private CustomerStatus status;

    /** 创建时间 */
    private LocalDateTime createdAt;

    /** 更新时间 */
    private LocalDateTime updatedAt;

    /**
     * 静态工厂方法 — 创建新客户
     */
    public static Customer create(String nickname, String email, String phone, String avatar, Gender gender) {
        Customer customer = new Customer();
        customer.setNickname(nickname);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAvatar(avatar);
        customer.setGender(gender != null ? gender : Gender.UNKNOWN);
        customer.setStatus(CustomerStatus.ACTIVE);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());
        return customer;
    }

    /**
     * 禁用客户
     */
    public void disable() {
        this.status = CustomerStatus.DISABLED;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 启用客户
     */
    public void enable() {
        this.status = CustomerStatus.ACTIVE;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 更新客户基本信息
     */
    public void updateProfile(String nickname, String avatar, Gender gender) {
        if (nickname != null) {
            this.nickname = nickname;
        }
        if (avatar != null) {
            this.avatar = avatar;
        }
        if (gender != null) {
            this.gender = gender;
        }
        this.updatedAt = LocalDateTime.now();
    }
}

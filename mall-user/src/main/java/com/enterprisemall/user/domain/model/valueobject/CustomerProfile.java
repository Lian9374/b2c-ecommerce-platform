package com.enterprisemall.user.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 客户资料值对象 — 包含非核心的展示性信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 个人简介 */
    private String bio;

    /** 生日 */
    private java.time.LocalDate birthday;

    /** 积分 */
    private Integer points;

    /** 等级 */
    private Integer level;

    /**
     * 创建默认资料
     */
    public static CustomerProfile createDefault() {
        CustomerProfile profile = new CustomerProfile();
        profile.setBio("");
        profile.setPoints(0);
        profile.setLevel(1);
        return profile;
    }

    /**
     * 增加积分
     */
    public CustomerProfile addPoints(Integer delta) {
        if (delta != null && delta > 0) {
            this.points = (this.points == null ? 0 : this.points) + delta;
        }
        return this;
    }

    /**
     * 升级
     */
    public CustomerProfile upgradeLevel(Integer newLevel) {
        if (newLevel != null && newLevel > 0) {
            this.level = newLevel;
        }
        return this;
    }
}

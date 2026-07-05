package com.enterprisemall.promotion.domain.event;

import com.enterprisemall.common.event.DomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 促销活动开始领域事件
 */
@Getter
public class PromotionStartedEvent extends DomainEvent {

    private final Long ruleId;
    private final String ruleName;
    private final LocalDateTime startedAt;

    public PromotionStartedEvent(Long ruleId, String ruleName) {
        super();
        this.ruleId = ruleId;
        this.ruleName = ruleName;
        this.startedAt = LocalDateTime.now();
    }
}

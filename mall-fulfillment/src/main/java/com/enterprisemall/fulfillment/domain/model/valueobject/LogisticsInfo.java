package com.enterprisemall.fulfillment.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Logistics tracking information value object — represents a single logistics trace node.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogisticsInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Current status description at this trace node (e.g. "In transit", "Out for delivery").
     */
    private String status;

    /**
     * Geographic location at this trace node (e.g. "Shanghai Distribution Center").
     */
    private String location;

    /**
     * Detailed description of this trace node (e.g. "Package arrived at sorting facility").
     */
    private String description;

    /**
     * Timestamp when this trace node was recorded.
     */
    private LocalDateTime timestamp;

    /**
     * Static factory method.
     */
    public static LogisticsInfo of(String status, String location, String description, LocalDateTime timestamp) {
        return new LogisticsInfo(status, location, description, timestamp);
    }
}

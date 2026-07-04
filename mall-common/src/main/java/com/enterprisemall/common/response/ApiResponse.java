package com.enterprisemall.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 统一 API 返回结构
 *
 * @param <T> 数据类型
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "统一返回结构")
public class ApiResponse<T> {

    @Schema(description = "状态码", example = "200")
    private final int code;

    @Schema(description = "消息", example = "success")
    private final String message;

    @Schema(description = "数据")
    private final T data;

    @Schema(description = "时间戳")
    private final LocalDateTime timestamp;

    // ==================== 成功 ====================

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(200, "success", null, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "success", data, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(200, message, data, LocalDateTime.now());
    }

    // ==================== 失败 ====================

    public static <T> ApiResponse<T> error(int code, String message) {
        return new ApiResponse<>(code, message, null, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> error(int code, String message, T data) {
        return new ApiResponse<>(code, message, data, LocalDateTime.now());
    }

    // ==================== 分页 ====================

    public static <T> ApiResponse<PageData<T>> page(PageData<T> pageData) {
        return new ApiResponse<>(200, "success", pageData, LocalDateTime.now());
    }

    @Getter
    @AllArgsConstructor
    @Schema(description = "分页数据")
    public static class PageData<T> {
        @Schema(description = "数据列表")
        private final java.util.List<T> records;
        @Schema(description = "总条数", example = "100")
        private final long total;
        @Schema(description = "当前页", example = "1")
        private final int page;
        @Schema(description = "每页大小", example = "20")
        private final int size;
    }
}

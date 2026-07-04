package com.enterprisemall;

import com.enterprisemall.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查接口
 */
@RestController
@RequestMapping("/api")
@Tag(name = "健康检查", description = "系统健康状态检查")
public class HealthController {

    @GetMapping("/health")
    @Operation(summary = "健康检查")
    public ApiResponse<String> health() {
        return ApiResponse.success("OK");
    }
}

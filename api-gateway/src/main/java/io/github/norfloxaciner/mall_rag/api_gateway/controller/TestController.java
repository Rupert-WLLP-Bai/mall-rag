package io.github.norfloxaciner.mall_rag.api_gateway.controller;

import io.github.norfloxaciner.mall_rag.common.response.ApiResponse;
import io.github.norfloxaciner.mall_rag.common.response.ErrorCode;
import io.github.norfloxaciner.mall_rag.infrastructure.logging.TraceIdFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "测试接口", description = "用于测试 API 网关是否正常工作")
@RestController
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/hello")
    @Operation(summary = "测试接口", description = "用于测试 API 网关是否正常工作")
    public String hello() {
        String traceId = MDC.get(TraceIdFilter.TRACE_ID);
        logger.info("调用 /hello 接口，TraceId = {}", traceId);
        return "Hello from api-gateway!";
    }

    @GetMapping("/fail")
    @Operation(summary = "失败接口", description = "用于测试 API 网关的错误处理")
    public ApiResponse<?> fail() {
        String traceId = MDC.get(TraceIdFilter.TRACE_ID);
        logger.warn("调用 /fail 接口，TraceId = {}", traceId);
        return ApiResponse.fail(ErrorCode.SYSTEM_ERROR.getCode(), ErrorCode.SYSTEM_ERROR.getMessage());
    }

    @GetMapping("/test_trace_id")
    @Operation(summary = "测试 Trace ID", description = "用于测试 Trace ID 是否正确传递")
    public ApiResponse<String> testTraceId() {
        String traceId = MDC.get(TraceIdFilter.TRACE_ID);
        logger.info("调用 /test_trace_id 接口，TraceId = {}", traceId);
        return ApiResponse.success(traceId);
    }
}

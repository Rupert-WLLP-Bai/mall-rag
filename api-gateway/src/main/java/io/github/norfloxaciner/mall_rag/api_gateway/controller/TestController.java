package io.github.norfloxaciner.mall_rag.api_gateway.controller;

import io.github.norfloxaciner.mall_rag.common.response.ApiResponse;
import io.github.norfloxaciner.mall_rag.common.response.ErrorCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from api-gateway!";
    }

    @GetMapping("/fail")
    public ApiResponse<?> fail() {
        return ApiResponse.fail(ErrorCode.SYSTEM_ERROR.getCode(), ErrorCode.SYSTEM_ERROR.getMessage());
    }
}

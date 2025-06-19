package io.github.norfloxaciner.mall_rag.common.exception;

import io.github.norfloxaciner.mall_rag.common.response.ApiResponse;
import io.github.norfloxaciner.mall_rag.common.response.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 业务异常处理
    @ExceptionHandler(BusinessException.class)
    public ApiResponse<?> handleBizEx(BusinessException e) {
        log.warn("Business exception: {}", e.getMessage());
        return ApiResponse.fail(e.getCode(), e.getMessage());
    }

    // 未知异常兜底处理
    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleOtherEx(Exception e) {
        log.error("System exception: ", e);  // 打印堆栈
        return ApiResponse.fail(ErrorCode.SYSTEM_ERROR.getCode(), ErrorCode.SYSTEM_ERROR.getMessage());
    }
}

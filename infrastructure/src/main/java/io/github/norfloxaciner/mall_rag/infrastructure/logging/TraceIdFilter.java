package io.github.norfloxaciner.mall_rag.infrastructure.logging;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.MDC;

import java.io.IOException;
import java.util.UUID;

public class TraceIdFilter implements Filter {

    public static final String TRACE_ID = "traceId";
    public static final String TRACE_ID_HEADER = "X-Trace-Id";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 从请求头获取 traceId，如果没有则生成一个新的
        String traceId = httpRequest.getHeader(TRACE_ID_HEADER);
        if (traceId == null || traceId.isBlank()) {
            traceId = UUID.randomUUID().toString();
        }

        // 调试用
        // System.out.println("==== TraceIdFilter 执行, traceId = " + traceId);

        // 将 traceId 存入 MDC 和响应头
        MDC.put(TRACE_ID, traceId);
        httpResponse.setHeader(TRACE_ID_HEADER, traceId);

        try {
            // 继续执行过滤链
            chain.doFilter(request, response);
        } finally {
            // 清理 MDC 中的 traceId，避免内存泄漏
            MDC.remove(TRACE_ID);
        }
    }

}

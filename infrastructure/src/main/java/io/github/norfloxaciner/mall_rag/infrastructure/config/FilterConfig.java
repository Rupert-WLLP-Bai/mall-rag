package io.github.norfloxaciner.mall_rag.infrastructure.config;

import io.github.norfloxaciner.mall_rag.infrastructure.logging.TraceIdFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> traceIdFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TraceIdFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("traceIdFilter");
        registrationBean.setOrder(1);  // 优先级，越小越早执行
        return registrationBean;
    }
}

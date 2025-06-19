package io.github.norfloxaciner.mall_rag.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "io.github.norfloxaciner.mall_rag.api_gateway",
    "io.github.norfloxaciner.mall_rag.infrastructure.logging",
    "io.github.norfloxaciner.mall_rag.infrastructure.config"
})
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
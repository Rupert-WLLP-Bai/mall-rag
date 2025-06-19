package io.github.norfloxaciner.mall_rag.infrastructure.swagger;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("mall-rag")
                .packagesToScan("io.github.norfloxaciner.mall_rag")
                .build();
    }

    @Bean
    public Info apiInfo() {
        return new Info()
                .title("Mall-RAG API Doc")
                .description("API文档, Knife4j + SpringDoc 示例")
                .version("1.0");
    }
}

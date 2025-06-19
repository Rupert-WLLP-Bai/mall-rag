# Mall-RAG

找不到暑期实习，被迫速成 Java，做到什么程度都随缘吧

## 结构

```
mall-rag/
├── api-gateway/               # API 网关
├── user-service/              # 用户服务
├── product-service/           # 商品与搜索服务
├── order-service/             # 订单服务
├── seckill-service/           # 秒杀服务
├── pay-service/               # 支付服务
├── rag-service/               # 智能问答（RAG）
├── recommend-service/         # 推荐模块（可选）
├── common/                    # 公共模块（工具类、通用响应等）
├── infrastructure/            # Redis/Kafka/ES等配置封装
└── deploy/                    # Dockerfile / Helm / CI脚本
```

## 选型

| 模块       | 选型                                    | 理由                      |
| ---------- | --------------------------------------- | ------------------------- |
| 网关       | Spring Cloud Gateway                    | Spring 生态成熟，简单灵活 |
| 鉴权限流   | JWT + Spring Security + Sentinel        | 面试常见，方案完整        |
| 微服务框架 | Spring Boot + Spring Cloud + Nacos      | 生态完善，资料丰富        |
| 服务通信   | OpenFeign                               | 简洁易用                  |
| 数据访问   | MyBatis-Plus                            | 快速开发                  |
| 分布式事务 | Seata                                   | 业界常用，集成方便        |
| 定时任务   | XXL-Job                                 | 分布式轻量调度            |
| 缓存       | Redis Cluster + Redisson                | 高可用，支持分布式锁      |
| 消息队列   | Kafka                                   | 高性能，面试加分          |
| 搜索       | ElasticSearch                           | 业界标准，功能丰富        |
| 向量搜索   | Milvus + OpenAI Embedding + LangChain4j | AI 亮点突出，市场趋势     |
| 数据库     | MySQL + ShardingSphere                  | 经典分库分表方案          |
| 容器       | Docker + Kubernetes + Helm              | 云原生基础                |
| 监控       | Prometheus + Grafana                    | 开源成熟                  |
| 日志       | ELK                                     | 日志管理标准              |
| 链路追踪   | SkyWalking                              | 多语言支持，阿里开源      |
| CI/CD      | GitHub Actions + ArgoCD                 | 云端 CI/CD + GitOps       |
| 安全       | Nacos 加密 + 滑动验证码 + Sentinel 限流 | 面试中易展示安全设计      |

# 速成计划

## Day 1 2025 年 6 月 19 日

1. 完成微服务模块 starter 创建，注意子模块 pom.xml 要写 parent
2. 定义通用响应结构和全局异常处理
3. 配置 MDC+TraceId，日志上下文追踪，定义之后注册到 SpringBoot
4. infrastructure 放 logback-base.xml，其他模块继承（注意每个模块都添加依赖 infrastructure）
5. SpringBoot3 不再用之前 springfox 的 Swagger，用 knife4j，新的配置方法
6. 基础类库，没有 main 方法，需要禁用该模块中的 spring-boot-maven-plugin，在子模块中加入

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <skip>true</skip> <!-- 跳过该模块的 repackage -->
            </configuration>
        </plugin>
    </plugins>
</build>
```

7. 微服务启动类需要扫描 infrastructure，显式指定

```java
@ComponentScan(basePackages = {
    "io.github.norfloxaciner.mall_rag.api_gateway",
    "io.github.norfloxaciner.mall_rag.infrastructure"
})
```

8. 所有日志输出都有 Trace Id

```java
2025-06-20 02:56:33.242 [http-nio-8088-exec-1] INFO  i.g.n.m.a.controller.TestController - [6a750fb7-5601-4afe-8c5c-df7dd10b8b82] 调用 /test_trace_id 接口，TraceId = 6a750fb7-5601-4afe-8c5c-df7dd10b8b82
2025-06-20 02:57:32.815 [http-nio-8088-exec-2] WARN  i.g.n.m.a.controller.TestController - [652dee5f-fde7-4877-94fe-55cee17b252c] 调用 /fail 接口，TraceId = 652dee5f-fde7-4877-94fe-55cee17b252c
2025-06-20 02:57:40.164 [http-nio-8088-exec-4] INFO  i.g.n.m.a.controller.TestController - [7e1355d4-00e6-4e03-adfc-b46e80a688ac] 调用 /hello 接口，TraceId = 7e1355d4-00e6-4e03-adfc-b46e80a688ac
```

> 2025-6-20 03:00:02 累了 毁灭吧

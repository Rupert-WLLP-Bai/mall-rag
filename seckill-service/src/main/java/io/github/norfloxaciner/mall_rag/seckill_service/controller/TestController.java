package io.github.norfloxaciner.mall_rag.seckill_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from seckill-service!";
    }
}

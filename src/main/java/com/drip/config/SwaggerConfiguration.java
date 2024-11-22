package com.drip.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI StringDocOpenApi(){
        return new OpenAPI().info(new Info()
                .title("跃动Forever测试网站-在线API接口文档")
                .description("测试网站接口文档,欢迎前端人员查阅")
                .version("2.0")
                .license(new License().name("我的B站首页").url("https://space.bilibili.com/299403687"))
        );
    }
}

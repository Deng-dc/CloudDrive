package com.afk.cloudrive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: dengcong
 * @Date: 2022/8/23 - 08 - 23 - 19:51
 * @Description: com.afk.cloudrive.config
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    // 默认文档地址为 localhost:8066/swagger-ui.html#
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.afk.cloudrive"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("CloudDrive项目api接口文档")
                .description("copyright reserved")
                .version("1.0")
                .build();
    }
}

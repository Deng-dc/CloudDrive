package com.afk.cloudrive.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: dengcong
 * @Date: 2022/8/24 - 08 - 24 - 9:20
 * @Description: com.afk.cloudrive.config
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${file-server.home-dir}")
    private String sysHomeDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/drive/**").addResourceLocations("file:" + sysHomeDir);
    }
}

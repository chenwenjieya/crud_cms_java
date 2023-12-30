package com.cj.config;

import com.cj.interceptor.UrlInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private UrlInterceptor urlInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(urlInterceptor).addPathPatterns("/*").excludePathPatterns("/user/login", "/swagger-ui**","/swagger-ui.html", "/swagger-resources/**");
    }

}

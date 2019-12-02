package com.finastra.vaoo.config;

import com.finastra.vaoo.service.SecurityService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {
    private String INVALID_TOKEN = "token is invalid: %s";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
                boolean isValid = SecurityService.validate(request.getHeader("token"));
                if (!isValid) {
                    throw new SecurityException(String.format(INVALID_TOKEN, Optional.ofNullable(request.getHeader("token")).orElse("\"\"")));
                }
                return isValid;
            }
        }).excludePathPatterns("/actuator/**","/swagger-ui.html","/webjars/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}

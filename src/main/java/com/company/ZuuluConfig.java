package com.company;

import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/*
* Конфигурация API Gateway
*/
@Configuration
@ComponentScan({"com.company"})
@EnableZuulProxy
public class ZuuluConfig {

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver() {
            @Override
            public boolean isMultipart(HttpServletRequest request) {
                String method = request.getMethod().toLowerCase();
                if (!Arrays.asList("put","post").contains(method)){
                    return false;
                }
                String contentType = request.getContentType();
                return contentType != null && contentType.toLowerCase().startsWith("multipart/");
            }
        };
    }
}

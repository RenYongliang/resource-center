package com.ryl.res.config;

import com.obs.services.ObsClient;
import com.ryl.res.config.jwt.interceptor.JwtInterceptor;
import com.ryl.res.config.obs.ObsProperties;
import com.ryl.res.util.SnowflakeIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ryl
 * @description:
 * @date: 2020-02-28 19:29:24
 */
@Configuration
public class ResCenterConfig implements WebMvcConfigurer {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludeUrls = new ArrayList<>();
        excludeUrls.add("/swagger-resources");
        excludeUrls.add("/v2/api-docs");
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**")
                .excludePathPatterns(excludeUrls);
    }

    @Bean
    public ObsClient obsClient(ObsProperties configParam){
        return new ObsClient(configParam.getAccessKey(),configParam.getSecretKey(),configParam.getEndPoint());
    }

    @Bean
    public SnowflakeIdGenerator snowflakeIdGenerator() {
        return new SnowflakeIdGenerator(0, 0);
    }

}

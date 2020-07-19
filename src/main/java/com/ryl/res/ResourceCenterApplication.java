package com.ryl.res;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@MapperScan(basePackages = {"com.ryl.res.mapper"})
@SpringBootApplication(scanBasePackages = {"com.ryl"})
public class ResourceCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceCenterApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}

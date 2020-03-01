package com.ryl.res;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@MapperScan(basePackages = {"com.ryl.res.mapper"})
@SpringBootApplication
public class ResourceCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceCenterApplication.class, args);
    }

}

package com.ryl.res.config.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ryl
 * @description:
 * @date: 2020-08-17 09:40:36
 */
@ComponentScan(basePackages = "com.ryl.res.config.test")
@Configuration
public class TestConfig {


    @Bean(initMethod = "aa",destroyMethod = "bb")
    Car car() {
        return new Car();
    }
}

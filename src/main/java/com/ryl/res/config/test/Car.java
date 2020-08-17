package com.ryl.res.config.test;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author: ryl
 * @description:
 * @date: 2020-08-17 09:41:52
 */
@Data
public class Car implements InitializingBean, DisposableBean {

    public Car() {
        System.out.println("car constructor...");
    }

    public void aa() {
        System.out.println("@Bean => initMethod()...");
    }

    public void bb() {
        System.out.println("@Bean => destroyMethod()...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean => destroy()...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("@InitializingBean => afterPropertiesSet()...");
    }

    @PostConstruct
    public void cc() {
        System.out.println("@PostConstruct...");
    }

    @PreDestroy
    public void dd() {
        System.out.println("@PreDestroy...");
    }
}

package com.ryl.res.config.test;

import lombok.Data;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author: ryl
 * @description:
 * @date: 2020-08-17 09:41:29
 */
@Data
public class Cat {

    public Cat() {
        System.out.println("cat constructor...");
    }

    @PostConstruct
    public void init() {
        System.out.println("cat init...");
    }


    @PreDestroy
    public void destroy() {
        System.out.println("cat destroy...");
    }
}

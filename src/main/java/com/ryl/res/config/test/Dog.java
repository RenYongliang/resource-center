package com.ryl.res.config.test;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author: ryl
 * @description:
 * @date: 2020-08-17 09:41:41
 */
@Data
public class Dog implements InitializingBean, DisposableBean {

    public Dog() {
        System.out.println("dog constructor...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("dog destroy...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("dog init...");
    }
}

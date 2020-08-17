package com.ryl.res;

import com.ryl.res.config.test.Car;
import com.ryl.res.config.test.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class ResourceCenterApplicationTests {

    @Test
    void contextLoads() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        Car car = (Car) context.getBean("car");
        context.close();
    }

}

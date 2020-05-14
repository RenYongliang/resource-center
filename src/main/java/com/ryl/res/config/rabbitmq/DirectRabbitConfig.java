package com.ryl.res.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ryl
 * @description:
 * @date: 2020-04-22 09:27:48
 */
@Configuration
public class DirectRabbitConfig {

    //队列 起名：testDirectQueue
    @Bean
    public Queue testDirectQueue(){
        return new Queue("testDirectQueue",true);
    }

    //Direct交换机 起名：testDirectExchange
    @Bean
    DirectExchange testDirectExchange(){
        return new DirectExchange("testDirectExchange");
    }

    //绑定 讲队列和交换机绑定，并设置用于匹配键：testDirectRouting
    @Bean
    Binding bindingDirect(){
        return BindingBuilder.bind(testDirectQueue()).to(testDirectExchange()).with("testDirectRouting");
    }
}

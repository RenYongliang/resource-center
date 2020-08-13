package com.ryl.res.mq.producer;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: ryl
 * @description:
 * @date: 2020-08-13 14:25:18
 */
@Component
public class HelloSender1 {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String x) {
        System.out.println("Sender1:" + x);
        rabbitTemplate.convertAndSend("ryl.exchange","ryl",x,new CorrelationData("202008131714"));
    }
}

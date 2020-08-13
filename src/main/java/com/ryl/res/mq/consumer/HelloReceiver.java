package com.ryl.res.mq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author: ryl
 * @description:
 * @date: 2020-08-13 14:28:31
 */
@Component
@RabbitListener(queues = "supply")
@Slf4j
public class HelloReceiver {


    @RabbitHandler
    public void process(@Payload String x, @Headers Map<String,Object> headers, Channel channel) throws IOException {
        log.info("消息接收成功！ ===========================>");
        log.info("消息内容:{}",x);
        Integer a = Integer.parseInt(x);
        // 消息签收
        channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG),false);
    }
}

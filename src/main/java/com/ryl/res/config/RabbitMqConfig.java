package com.ryl.res.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ryl
 * @description:
 * @date: 2020-08-13 14:04:40
 */
@Configuration
@Slf4j
public class RabbitMqConfig {


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        //若使用confirm-callback或return-callback，必须要配置publisherConfirms或publisherReturns为true
        //每个rabbitTemplate只能有一个confirm-callback和return-callback，如果这里配置了，那么写生产者的时候不能再写confirm-callback和return-callback
        //使用return-callback时必须设置mandatory为true，或者在配置中设置mandatory-expression的值为true

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
//        /**
//         * 如果消息没有到exchange,则confirm回调,ack=false
//         * 如果消息到达exchange,则confirm回调,ack=true
//         * exchange到queue成功,则不回调return
//         * exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
//         */
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            String flag = ack ? "成功" : "失败";
            log.info("消息发送{}！ ===========================>",flag);
            log.info("消息唯一标识:{};确认状态:{};造成原因:{}", correlationData.getId(), ack, cause);
        });
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("消息丢失！ ===========================>");
            log.info("消息主体:{};回复编码:{};回复内容:{};交换器:{};路由键:{}", message, replyCode, replyText, exchange, routingKey);
        });
        return rabbitTemplate;
    }
}

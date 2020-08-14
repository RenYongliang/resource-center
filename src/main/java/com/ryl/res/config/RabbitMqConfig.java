package com.ryl.res.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
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

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("supply");              // 监听的队列
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);        // 手动确认
        container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {      //消息处理
            System.out.println("====接收到消息=====");
            System.out.println(new String(message.getBody()));
            if(message.getMessageProperties().getHeader("error") == null){
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                System.out.println("消息已经确认");
            }else {
                //channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
                channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
                System.out.println("消息拒绝");
            }

        });
        return container;
    }

//    @Bean
//    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames("consumer_queue");              // 监听的队列
//        container.setAcknowledgeMode(AcknowledgeMode.AUTO);     // 根据情况确认消息
//        container.setMessageListener((message) -> {
//            System.out.println("====接收到消息=====");
//            System.out.println(new String(message.getBody()));
//            //抛出NullPointerException异常则重新入队列
//            //throw new NullPointerException("消息消费失败");
//            //当抛出的异常是AmqpRejectAndDontRequeueException异常的时候，则消息会被拒绝，且requeue=false
//            //throw new AmqpRejectAndDontRequeueException("消息消费失败");
//            //当抛出ImmediateAcknowledgeAmqpException异常，则消费者会被确认
//            throw new ImmediateAcknowledgeAmqpException("消息消费失败");
//        });
//        return container;
//    }
}

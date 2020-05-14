package com.ryl.res.service;

import com.ryl.res.model.dto.MessageDTO;
import com.ryl.res.model.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: ryl
 * @description:
 * @date: 2020-04-22 09:58:32
 */
@Component
@RabbitListener(queues = "testDirectQueue")
public class DirectReceiver {

    @RabbitHandler
    public void process(MessageDTO messageDTO){
        User user = (User) messageDTO.getMessageData();
        String str = String.format("DirectReceiver消费者收到消息，用户id:%s,用户名:%s,用户手机:%s", user.getUserId(), user.getName(), user.getPhone());
        System.out.println(str);
    }
}

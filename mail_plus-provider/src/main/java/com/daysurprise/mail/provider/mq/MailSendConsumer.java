package com.daysurprise.mail.provider.mq;

import com.alibaba.fastjson.JSONObject;
import com.daysurprise.mail.api.MailApi;
import com.daysurprise.mail.api.dto.EmailDto;
import com.daysurprise.mail.common.constants.MessageQueueConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Class: com.daysurprise.mail.mq.MailSendConsumer
 * @Author: daysurprise
 * @Date: 2021/3/27
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc: 邮件发送消费者
 */
@Component
@Slf4j
public class MailSendConsumer {

    @Autowired
    MailApi mailApi;

    @RabbitHandler
    @RabbitListener(queues = MessageQueueConstant.MAIL_SEND_QUEUE)
    public void onMessage(String msg) throws Exception {
        log.info("接收到队列 {} 中的消息: {}",MessageQueueConstant.MAIL_SEND_QUEUE,msg);
        if (null != msg){
            EmailDto email = JSONObject.parseObject(msg, EmailDto.class);
            mailApi.sendHtml(email);
        }
    }
}

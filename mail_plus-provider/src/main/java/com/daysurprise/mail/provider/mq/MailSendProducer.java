package com.daysurprise.mail.provider.mq;

import com.alibaba.fastjson.JSONObject;
import com.daysurprise.mail.api.dto.EmailDto;
import com.daysurprise.mail.common.constants.MessageQueueConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Class: com.daysurprise.mail.mq.MailSendProducer
 * @Author: daysurprise
 * @Date: 2021/3/27
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc: 邮件mq发送类
 */
@Service
@Slf4j
public class MailSendProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /***
     * 实时发送
     * @param email
     */
    public void sendInRealTime(EmailDto email){
        log.info("向队列{}中发送消息{}", MessageQueueConstant.MAIL_SEND_QUEUE, JSONObject.toJSONString(email));
        rabbitTemplate.convertAndSend(MessageQueueConstant.MAIL_SEND_EXCHANGE, MessageQueueConstant.MAIL_SEND_QUEUE_KEY, JSONObject.toJSONString(email));
    };

    /***
     * 5分钟延迟发送
     * @param email
     */
    public void sendAfterFiveMinute(EmailDto email){
        log.info("向队列{}中发送消息{}", MessageQueueConstant.MAIL_SEND_DLX_QUEUE, JSONObject.toJSONString(email));
        rabbitTemplate.convertAndSend(MessageQueueConstant.MAIL_SEND_EXCHANGE, MessageQueueConstant.MAIL_SEND_DLX_QUEUE_KEY, JSONObject.toJSONString(email));
    };
    /***
     * 15分钟延迟发送
     * @param email
     */
    public void sendAfterFifteenMinute(EmailDto email){
        log.info("向队列{}中发送消息{}", MessageQueueConstant.MAIL_SEND_DLX_FIFTEEN_QUEUE, JSONObject.toJSONString(email));
        rabbitTemplate.convertAndSend(MessageQueueConstant.MAIL_SEND_EXCHANGE, MessageQueueConstant.MAIL_SEND_DLX_FIFTEEN_QUEUE_KEY, JSONObject.toJSONString(email));
    };
    /***
     * 30分钟延迟发送
     * @param email
     */
    public void sendAfterThirtyMinute(EmailDto email){
        log.info("向队列{}中发送消息{}", MessageQueueConstant.MAIL_SEND_DLX_THIRTY_QUEUE, JSONObject.toJSONString(email));
        rabbitTemplate.convertAndSend(MessageQueueConstant.MAIL_SEND_EXCHANGE, MessageQueueConstant.MAIL_SEND_DLX_THIRTY_QUEUE_KEY, JSONObject.toJSONString(email));
    };
}

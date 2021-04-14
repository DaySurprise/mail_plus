package com.daysurprise.mail.common.constants;

/**
 * @Class: com.daysurprise.mail.constants.MessageQueueConstant
 * @Author: daysurprise
 * @Date: 2021/3/27
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc: 邮件mq消息常量
 */
public class MessageQueueConstant {

    public final static String MAIL_SEND_EXCHANGE = "MAIL_SEND_EXCHANGE";

    /***
     * 待消费的队列
     */
    public final static String MAIL_SEND_QUEUE = "MAIL_SEND_QUEUE";
    public final static String MAIL_SEND_QUEUE_KEY = "MAIL_SEND_QUEUE_KEY";

    /***
     * 死信队列 超时后会将消息发往 MAIL_SEND_QUEUE 最后完成了延迟队列的效果 5分钟
     */
    public final static String MAIL_SEND_DLX_QUEUE = "MAIL_SEND_DLX_QUEUE";
    public final static String MAIL_SEND_DLX_QUEUE_KEY = "MAIL_SEND_DLX_QUEUE_KEY";

    /***
     * 死信队列 超时后会将消息发往 MAIL_SEND_QUEUE 最后完成了延迟队列的效果 15分钟
     */
    public final static String MAIL_SEND_DLX_FIFTEEN_QUEUE = "MAIL_SEND_DLX_FIFTEEN_QUEUE";
    public final static String MAIL_SEND_DLX_FIFTEEN_QUEUE_KEY = "MAIL_SEND_DLX_FIFTEEN_QUEUE_KEY";

    /***
     * 死信队列 超时后会将消息发往 MAIL_SEND_QUEUE 最后完成了延迟队列的效果 30分钟
     */
    public final static String MAIL_SEND_DLX_THIRTY_QUEUE = "MAIL_SEND_DLX_THIRTY_QUEUE";
    public final static String MAIL_SEND_DLX_THIRTY_QUEUE_KEY = "MAIL_SEND_DLX_THIRTY_QUEUE_KEY";

    /***
     * 死信队列发送列延迟时间 5 分钟
     */
    public final static Integer MAIL_SEND_TTL = 5 * 1000;
    /***
     * 死信队列发送列延迟时间 15 分钟
     */
    public final static Integer MAIL_SEND_TTL_FIFTEEN = 15 * 1000;
    /***
     * 死信队列发送列延迟时间 30 分钟
     */
    public final static Integer MAIL_SEND_TTL_THIRTY = 30 * 1000;
}

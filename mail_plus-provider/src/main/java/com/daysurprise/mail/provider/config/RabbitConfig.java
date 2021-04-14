package com.daysurprise.mail.provider.config;

import com.daysurprise.mail.common.constants.MessageQueueConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Class: com.emax.channel.provider.modules.levypaymentflow.provider.LevyPaymentApiImpl
 * @Author: daysurprise
 * @Date: 2021/3/10
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc: RabbitMQ配置
 */
@Configuration
@Slf4j
public class RabbitConfig {

    /*@Value("${spring.rabbitmq.addresses}")
    private String addresses;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(addresses);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }*/

    //Direct交换机 起名：userSettlementExchange
    @Bean
    DirectExchange mailSendExchange() {
        // return new DirectExchange("userSettlementExchange",true,true);
        return new DirectExchange(MessageQueueConstant.MAIL_SEND_EXCHANGE,true,false);
    }

    /***
     * 实时队列
     * @return
     */
    @Bean
    public Queue mailSendQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        // return new Queue("userSettlementCreditQueue",true,true,false);

        // 一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue(MessageQueueConstant.MAIL_SEND_QUEUE,true);
    }
    //绑定  将队列和交换机绑定, 并设置用于匹配键：UserSettlementRouting
    @Bean
    Binding mailSendQueueBind() {
        return BindingBuilder.bind(mailSendQueue()).to(mailSendExchange()).with(MessageQueueConstant.MAIL_SEND_QUEUE_KEY);
    }

    /***
     * 5 分钟死信队列
     * @return
     */
    @Bean
    public Queue mailSendDlxQueue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", MessageQueueConstant.MAIL_SEND_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", MessageQueueConstant.MAIL_SEND_QUEUE_KEY);
        arguments.put("x-message-ttl", MessageQueueConstant.MAIL_SEND_TTL);
        Queue queue = new Queue(MessageQueueConstant.MAIL_SEND_DLX_QUEUE, true, false, false, arguments);
        return queue;
    }

    @Bean
    public Binding mailSendDlxQueueBind() {
        return new Binding(MessageQueueConstant.MAIL_SEND_DLX_QUEUE, Binding.DestinationType.QUEUE,
                MessageQueueConstant.MAIL_SEND_EXCHANGE, MessageQueueConstant.MAIL_SEND_DLX_QUEUE_KEY, null);
    }


    /***
     * 15 分钟死信队列
     * @return
     */
    @Bean
    public Queue mailSendDlxFifteenQueue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", MessageQueueConstant.MAIL_SEND_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", MessageQueueConstant.MAIL_SEND_QUEUE_KEY);
        arguments.put("x-message-ttl", MessageQueueConstant.MAIL_SEND_TTL_FIFTEEN);
        Queue queue = new Queue(MessageQueueConstant.MAIL_SEND_DLX_FIFTEEN_QUEUE, true, false, false, arguments);
        return queue;
    }

    @Bean
    public Binding mailSendDlxFifteenQueueBind() {
        return new Binding(MessageQueueConstant.MAIL_SEND_DLX_FIFTEEN_QUEUE, Binding.DestinationType.QUEUE,
                MessageQueueConstant.MAIL_SEND_EXCHANGE, MessageQueueConstant.MAIL_SEND_DLX_FIFTEEN_QUEUE_KEY, null);
    }

    /***
     * 30 分钟死信队列
     * @return
     */
    @Bean
    public Queue mailSendDlxThirtyQueue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", MessageQueueConstant.MAIL_SEND_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", MessageQueueConstant.MAIL_SEND_QUEUE_KEY);
        arguments.put("x-message-ttl", MessageQueueConstant.MAIL_SEND_TTL_THIRTY.shortValue());
        Queue queue = new Queue(MessageQueueConstant.MAIL_SEND_DLX_THIRTY_QUEUE, true, false, false, arguments);
        return queue;
    }

    @Bean
    public Binding mailSendDlxThirtyQueueBind() {
        return new Binding(MessageQueueConstant.MAIL_SEND_DLX_THIRTY_QUEUE, Binding.DestinationType.QUEUE,
                MessageQueueConstant.MAIL_SEND_EXCHANGE, MessageQueueConstant.MAIL_SEND_DLX_THIRTY_QUEUE_KEY, null);
    }
}

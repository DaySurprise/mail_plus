package com.daysurprise.mail.provider.modules.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Class: com.daysurprise.mail.model.PO.Email
 * @Author: daysurprise
 * @Date: 2021/3/30
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc: 邮件实体
 */
@Data
@TableName("email")
public class Email implements Serializable {
    /***
     * 自增编号
     */
    @TableId(type = IdType.INPUT)
    private Integer id;
    /***
     * 邮件编号
     */
    private String mailId;
    /***
     * 收件者
     */
    private String receivers;
    /**
     * 主题
     */
    private String subject;
    /**
     * 内容
     */
    private String content;
    /**
     * 模板
     */
    private String template;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 发送时间
     */
    private Date sendTime;
    /**
     * 发送结果
     */
    private String resultMsg;
}

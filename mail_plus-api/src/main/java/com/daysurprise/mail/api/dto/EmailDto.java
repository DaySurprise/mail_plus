package com.daysurprise.mail.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Class: com.emax.etl.rpcapi.mail.dto.EmailDto
 * @Author: daysurprise
 * @Date: 2021/4/7
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
@Data
public class EmailDto implements Serializable {
    /***
     * 自增编号
     */
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
    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 每页大小
     */
    private Integer pageSize;
    /***
     * 文件地址列表
     */
    private List<String> fileUrlList;
}

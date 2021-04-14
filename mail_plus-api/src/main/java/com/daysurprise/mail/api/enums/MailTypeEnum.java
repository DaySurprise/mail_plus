package com.daysurprise.mail.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * @Class: com.daysurprise.mail.api.enums.MailTypeEnum
 * @Author: daysurprise
 * @Date: 2021/4/14
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc: 邮件类型type
 */
@Getter
@AllArgsConstructor
public enum MailTypeEnum implements Serializable {
    /***
     * 纯文本
     */
    TXT("纯文本"),
    /***
     * HTML格式
     */
    HTML("HTML格式"),
    /***
     * 带附件
     */
    ATTACH("带附件");

    private String message;
}

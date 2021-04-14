package com.daysurprise.mail.api;

import com.daysurprise.mail.api.dto.EmailDto;

import java.util.List;

/**
 * @Class: com.emax.etl.rpcapi.mail.MailApi
 * @Author: daysurprise
 * @Date: 2021/4/7
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public interface MailApi {

    Result<List<EmailDto>> list(EmailDto emailDto);

    void send(EmailDto mail);

    void sendHtml(EmailDto mail);

    void sendHtmlWithFile(EmailDto mail);

    /***
     * 通过邮件编号查询邮件
     * @param mailId 邮件编号
     * @return
     */
    EmailDto queryEmailById(String mailId);
}

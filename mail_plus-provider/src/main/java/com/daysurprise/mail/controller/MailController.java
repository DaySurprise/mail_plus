package com.daysurprise.mail.controller;

import com.alibaba.fastjson.JSONObject;
import com.daysurprise.mail.api.MailApi;
import com.daysurprise.mail.api.Result;
import com.daysurprise.mail.api.dto.EmailDto;
import com.daysurprise.mail.provider.mq.MailSendProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Class: com.daysurprise.mail.controller.MailController
 * @Author: daysurprise
 * @Date: 2021/4/7
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
@RestController
@Slf4j
public class MailController {

    @Autowired
    MailApi mailApi;

    @Autowired
    MailSendProducer mailSendProducer;

    @GetMapping("/")
    public String index(){
        return "ok";
    }

    @GetMapping("/mail/list")
    @ResponseBody
    public String list(Integer pageNum){
        EmailDto emailDto = new EmailDto();
        emailDto.setPageNum(pageNum);
        emailDto.setPageSize(10);
        Result<List<EmailDto>> list = mailApi.list(emailDto);
        return JSONObject.toJSONString(list);
    }

    @GetMapping("/mail/preview/{mailId}")
    public void preview(@PathVariable String mailId, HttpServletResponse response) throws IOException {
        EmailDto dto = mailApi.queryEmailById(mailId);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(dto.getContent());
    }

    @RequestMapping("/mail/send")
    @ResponseBody
    public String send(EmailDto emailDto){
        mailApi.send(emailDto);
        return "ok";
    }

    @RequestMapping("/mail/sendMq")
    @ResponseBody
    public String sendMq(EmailDto emailDto){
        mailSendProducer.sendInRealTime(emailDto);
        return "ok";
    }

    @RequestMapping("/mail/sendHtml")
    @ResponseBody
    public String sendHtml(EmailDto emailDto){
        mailApi.sendHtml(emailDto);
        return "ok";
    }

    @RequestMapping("/mail/sendHtmlWithFile")
    @ResponseBody
    public String sendHtmlWithFile(EmailDto emailDto){
        log.info("###/sendHtmlWithFile 发送带附件的邮件入参:{}", JSONObject.toJSONString(emailDto));
        mailApi.sendHtmlWithFile(emailDto);
        return "ok";
    }
}

package com.daysurprise.mail.provider.modules.provider;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.daysurprise.mail.api.MailApi;
import com.daysurprise.mail.api.Result;
import com.daysurprise.mail.api.dto.EmailDto;
import com.daysurprise.mail.provider.modules.entity.Email;
import com.daysurprise.mail.provider.modules.manager.MailManager;
import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Class: com.emax.etl.provider.modules.provider.MailApiImpl
 * @Author: daysurprise
 * @Date: 2021/4/7
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc: 邮件发送dubbo接口实现
 */
@Service
@Slf4j
public class MailApiImpl implements MailApi {

    @Autowired
    MailManager mailManager;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    public String userName;

    @Value("${spring.mail.alias}")
    public String alias;

    @Autowired
    public Configuration configuration;

    private String path = "/";

    @Override
    public Result<List<EmailDto>> list(EmailDto emailDto) {
        log.info("###/list 查询邮件列表: {}", emailDto);
        LambdaQueryWrapper<Email> wrapper = new QueryWrapper<Email>().lambda().orderByDesc(Email::getCreateTime);
        Page<Email> page = new Page<>(emailDto.getPageNum(), emailDto.getPageSize());
        IPage<Email> emailPage = mailManager.page(page, wrapper);
        List<EmailDto> list = new ArrayList<>();
        emailPage.getRecords().forEach(email ->{
            EmailDto dto = convertDoToDto(email);
            list.add(dto);
        });
        return Result.success(list);
    }

    @Override
    public EmailDto queryEmailById(String mailId) {
        log.info("###/queryEmailById 查询单个邮件信息:{}", mailId);
        LambdaQueryWrapper<Email> wrapper = new QueryWrapper<Email>().lambda().eq(Email::getMailId, mailId);
        Email email = mailManager.getOne(wrapper);
        if (null == email){
            return null;
        }
        return convertDoToDto(email);
    }

    @Override
    public void send(EmailDto mail){
        log.info("###/send 发送文本邮件:{}", mail);
        sendHtmlWithFile(mail);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendHtml(EmailDto mail) {
        log.info("###/sendHtml 发送Html邮件:{}", mail);
        sendHtmlWithFile(mail);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendHtmlWithFile(EmailDto mail) {
        log.info("###/sendHtmlWithFile 发送带附件邮件:{}", mail);
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(userName,alias);
            helper.setTo(mail.getReceivers());
            helper.setSubject(mail.getSubject());
            helper.setText(
                    mail.getContent(),
                    true);
            if (!CollectionUtils.isEmpty(mail.getFileUrlList())){
                for (String fileUrl : mail.getFileUrlList()) {
                    File file = getFile(fileUrl);
                    helper.addAttachment(getFileNameByUrl(fileUrl) + getFileSuffixByUrl(fileUrl), file);
                }
            }
            Email email = convertDtoToDo(mail);
            mailManager.save(email);
            mailSender.send(message);
        }catch (Exception e){
            log.error("###/sendHtmlWithFile 发送带附件邮件异常:", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    /***
     * 将网络文件url转换为File对象
     * @param url
     * @return
     * @throws Exception
     */
    private File getFile(String url) {
        String fileName = getFileSuffixByUrl(url);
        File file = null;

        URL urlfile;
        InputStream inStream = null;
        OutputStream os = null;
        try {
            file = File.createTempFile("net_url", fileName);
            //下载
            urlfile = new URL(url);
            inStream = urlfile.openStream();
            os = new FileOutputStream(file);

            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inStream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            log.info("###/ 图片地址转换为File对象异常: ", e);
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
                if (null != inStream) {
                    inStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }
    /***
     * 从文件路径中获取文件名称
     * @param fileUrl
     * @return
     */
    private String getFileNameByUrl(String fileUrl) {
        String[] names1 = fileUrl.split("\\.");
        String[] names2 = names1[names1.length - 2].split("\\/");
        return names2[names2.length -1];
    }

    /***
     * 从文件路径中获取文件后缀
     * @param fileUrl
     * @return
     */
    private String getFileSuffixByUrl(String fileUrl){
        return fileUrl.substring(fileUrl.lastIndexOf("."),fileUrl.length());
    }

    /***
     * do转换为dto
     * @param email
     * @return
     */
    private EmailDto convertDoToDto(Email email) {
        EmailDto emailDto = new EmailDto();
        BeanUtils.copyProperties(email,emailDto);
        return emailDto;
    }



    /***
     * dto转换为do
     * @param emailDto
     * @return
     */
    private Email convertDtoToDo(EmailDto emailDto) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        email.setMailId(UUID.randomUUID().toString());
        email.setCreateTime(new Date());
        email.setSendTime(new Date());
        return email;
    }

}

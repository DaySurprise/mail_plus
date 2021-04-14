package com.daysurprise.mail.provider.modules.manager;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daysurprise.mail.provider.modules.entity.Email;
import com.daysurprise.mail.provider.modules.mapper.MailMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Class: com.emax.etl.provider.modules.mapper.MailMapper
 * @Author: daysurprise
 * @Date: 2021/4/7
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
@Slf4j
@Service
public class MailManager extends ServiceImpl<MailMapper, Email> implements IService<Email> {


}

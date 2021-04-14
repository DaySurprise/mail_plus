package com.daysurprise.mail.provider.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Class: com.emax.etl.provider.modules.mapper.MailMapper
 * @Author: daysurprise
 * @Date: 2021/4/7
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc: MybatisPlus配置类
 */
@Configuration
@MapperScan(value={"com.daysurprise.mail.provider.modules.mapper*"})
public class MybatisPlusConfig {

    /**
         *  分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {

        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();

        // 攻击 SQL 阻断解析器、加入解析链 防止 delete update 全表操作
        sqlParserList.add(new BlockAttackSqlParser());
        paginationInterceptor.setSqlParserList(sqlParserList);
        // 设置sql的limit为无限制，默认是500
        paginationInterceptor.setLimit(-1);

        return paginationInterceptor;
    }
    
//    /**
//     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
//     */
//    @Bean
//    public PerformanceInterceptor performanceInterceptor() {
//        return new PerformanceInterceptor();
//    }


   
}

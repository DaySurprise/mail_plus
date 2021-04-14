

# mail_plus 简介
邮件服务-基于springboot,mybatis-plus,redis,rabbitmq,dubbo技术框架实现的一个邮件增加服务,主要支持多种方式发送不同种类的邮件。单独部署和维护，与其他系统解耦。
项目可单独部署: 其他项目通过dubbo, http, 消息 发送邮件。

# 软件架构
此项目采用SpringBoot+Mybatis构建,主要使用的技术点有springboot,mybatis,spring,springmvc,dubbo,rabbitmq,redis等。

# 功能介绍
1. 文本邮件发送
2. Html格式邮件发送
3. 带附件邮件发送
4. mq发送邮件
5. dubbo接口调用发送邮件
6. http发送邮件
7. 延时发送邮件

# 使用教程
1. 克隆项目
2. 通过 idea / eclipse 导入项目
3. 运行 ServerApplication 类即可


# 使用说明

1. http发送邮件没有加密,此项待开发
2. 后续应该加上邮件状态管理及异常补偿

# 参与贡献

1. 欢迎使用与沟通
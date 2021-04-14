package com.daysurprise.mail.provider;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
@EnableDubbo
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(value = {"com.daysurprise.mail"})
public class ServerApplication {

  public static void main(String[] args) throws UnknownHostException {
    ConfigurableApplicationContext application = SpringApplication.run(ServerApplication.class, args);
    Environment env = application.getEnvironment();
    String ip = InetAddress.getLocalHost().getHostAddress();
    String port = env.getProperty("server.port");
    log.info("\n----------------------------------------------------------\n\t" +
        "Application server-provider-Boot is running! Access URLs:\n\t" +
        "Local: \t\thttp://localhost:" + port+"/\n\t" +
        "External: \thttp://" + ip + ":" + port + "/\n\t" +
        "----------------------------------------------------------");
  }
}
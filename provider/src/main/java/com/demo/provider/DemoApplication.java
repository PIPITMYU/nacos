package com.demo.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.demo.provider.mapper")
@ComponentScan({"com.demo.provider.*","com.demo.redisson.*"})
@SpringBootApplication
@EnableDiscoveryClient
//开启自定义定时任务
@EnableScheduling
//启用@Retryable
@EnableRetry
public class DemoApplication {

    public static void main(String[] args) {
//        SpringApplication.run(DemoApplication.class, args);
        SpringApplication springApplication = new SpringApplication(DemoApplication.class);
        // 禁用命令行参数
        springApplication.setAddCommandLineProperties(false);
        springApplication.run(args);

    }

}

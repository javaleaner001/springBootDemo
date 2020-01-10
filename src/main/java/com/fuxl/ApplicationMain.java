package com.fuxl;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2 //http://localhost:8081/springBoot/swagger-ui.html
@SpringBootApplication
@EnableScheduling //定时任务
//@EnableAsync //多线程异步可配置在相应configuration类中（com.fuxl.async.ExecutorConfig）
@MapperScan(basePackages = "com.fuxl.mapper")
public class ApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class);
    }
}

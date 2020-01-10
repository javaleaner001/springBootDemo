package com.fuxl.scheduled;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class ScheduledDemo {

    @Scheduled(fixedRate = 5000,initialDelay = 3000)//启动三秒后没五秒执行一次
    //cron表达式
    public void demo(){
        System.out.println("schedule");
    }
}

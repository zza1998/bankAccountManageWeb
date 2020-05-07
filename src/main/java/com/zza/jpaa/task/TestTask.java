package com.zza.jpaa.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
@Slf4j
public class TestTask {

//    @Scheduled(cron = "0/20 * * * * ?")
//    public void test1(){
//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        log.info(Thread.currentThread().getName()+"每20秒打印一次");
//    }
//
//    @Scheduled(cron = "0/5 * * * * ?",zone = "Asia/Shanghai")
//    public void test2(){
//        log.info(Thread.currentThread().getName()+"每5秒打印一次");
//    }
}

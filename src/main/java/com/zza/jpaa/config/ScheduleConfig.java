package com.zza.jpaa.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    private volatile AtomicInteger threadFlag = new AtomicInteger(0);

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(getScheduleThreadPool());
    }

    @Bean
    public ExecutorService getScheduleThreadPool() {
        return new ScheduledThreadPoolExecutor(10,scheduleThreadFactory());
    }

    @Bean
    public ThreadFactory scheduleThreadFactory() {
        return r -> {
            Thread t = new Thread(r);
            t.setName("TaskThread ---" + threadFlag.getAndIncrement());
            return t;
        };
    }
}

package com.zza.jpaa.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitConfig implements CommandLineRunner {



    @Override
    public void run(String... args) throws Exception {
        System.out.println("初始化完毕");
    }
}

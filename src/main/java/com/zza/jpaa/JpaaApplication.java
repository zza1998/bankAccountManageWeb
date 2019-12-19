package com.zza.jpaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class JpaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaaApplication.class, args);
    }

}

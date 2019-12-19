package com.zza.jpaa.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class springAop {

    @Pointcut("execution(* com.zza.jpaa..controller.*.*(..))")
    private void printCut(){

    }

    @Around("printCut()")
    public Object executeLog(ProceedingJoinPoint point){
        long start = System.currentTimeMillis();
        Object proceed = null;
        try{
           proceed = point.proceed();
        } catch (Throwable e){
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时 + "+ (end-start) );
        return proceed;
    }
}

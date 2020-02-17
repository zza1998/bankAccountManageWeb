package com.zza.jpaa.exception;

import com.zza.jpaa.common.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionHand {

    @ExceptionHandler(value = {Exception.class,BizException.class})
    public ResultData  allExceptionHandle(HttpServletRequest request, Exception e){
        if(e instanceof BizException){
            BizException exc = (BizException)e;
            ResultData resultData = new ResultData<>(exc.getCode(),exc.getData(),exc.getMsg());
            log.error("业务出错 {}",exc.getMsg(), e);
            return resultData;
        }
        log.error(e.getMessage(),e);
        return ResultData.fail("系统错误, 请联系客服人员");
    }



}


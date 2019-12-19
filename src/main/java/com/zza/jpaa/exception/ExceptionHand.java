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

    @ExceptionHandler(value = Exception.class)
    public ResultData  allExceptionHandle(HttpServletRequest request, Exception e){
        log.error(e.getMessage(),e);
        return ResultData.fail("系统错误, 请联系客服人员");
    }

    @ExceptionHandler(value = BizException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData myExceptionHandle(HttpServletRequest request, BizException e){
        ResultData resultData = new ResultData<>(e.getCode(),e.getData(),e.getMsg());
        log.error("业务出错 {}",e.getMsg(), e);
        return resultData;
    }

}


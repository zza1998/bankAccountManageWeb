package com.zza.jpaa.controller;

import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.entity.OperatorLog;
import com.zza.jpaa.entity.dto.LogDto;
import com.zza.jpaa.services.LogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {

    @Resource
    LogService logService;

    @GetMapping("/list")
    public ResultData getLogList(){
        List<LogDto> logList = logService.getLogList();
        return ResultData.success("获取成功",logList);
    }
}

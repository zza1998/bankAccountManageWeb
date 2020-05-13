package com.zza.jpaa.controller;

import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.entity.dto.LogListDto;
import com.zza.jpaa.entity.vo.LogListVo;
import com.zza.jpaa.services.LogService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/log")
public class LogController {

    @Resource
    LogService logService;


    @GetMapping("/list")
    public ResultData getLogList(LogListVo logListVo){
        if (StringUtils.isEmpty(logListVo.getPageSize())){
            logListVo.setPageSize(10);
        }
        if (StringUtils.isEmpty(logListVo.getPageIndex())){
            logListVo.setPageIndex(1);
        }
        LogListDto logList = logService.getLogList(logListVo);
        return ResultData.success("获取成功",logList);
    }

    @PostMapping("/{id}")
    public ResultData delLog(@PathVariable String id){
        logService.delLog(id);
        return ResultData.success();
    }

    @GetMapping("/type")
    public ResultData getType(){
       return logService.getTpe();
    }
}

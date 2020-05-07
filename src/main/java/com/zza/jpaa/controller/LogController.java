package com.zza.jpaa.controller;

import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.entity.dto.LogListDto;
import com.zza.jpaa.services.LogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/log")
public class LogController {

    @Resource
    LogService logService;


    @GetMapping("/list")
    public ResultData getLogList(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        LogListDto logList = logService.getLogList(pageNum,pageSize);
        return ResultData.success("获取成功",logList);
    }

    @PostMapping("/{id}")
    public ResultData delLog(@PathVariable String id){
        logService.delLog(id);
        return ResultData.success();
    }
}

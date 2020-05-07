package com.zza.jpaa.controller;


import com.zza.jpaa.annotion.CurrentUser;
import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.constant.enums.MessageTypeEnum;
import com.zza.jpaa.entity.Message;
import com.zza.jpaa.entity.dto.MessageDto;
import com.zza.jpaa.entity.dto.UserInfo;
import com.zza.jpaa.entity.vo.MessageVo;
import com.zza.jpaa.services.MessageService;
import org.apache.tomcat.jni.Time;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/msg")
public class MessageController {

    @Resource
    MessageService messageService;


    @GetMapping("/list")
    public ResultData getMessages(@CurrentUser UserInfo userInfo){
        List<MessageDto> userMessages = messageService.getUserMessages(userInfo.getUserId(), false);
        return ResultData.success("获取成功",userMessages);
    }

    @PostMapping("/send")
    public ResultData sendMessage(@CurrentUser UserInfo userInfo, @RequestBody MessageVo messageVo){
        messageService.send(userInfo.getUserId(),messageVo.getTitle(),messageVo.getContent());
        return ResultData.success("发送成功");
    }

    @PostMapping("/del/{id}")
    public ResultData delMessage(@PathVariable(value = "id") Integer id){
        messageService.delMessage(id);
        return ResultData.success("删除成功");
    }
}

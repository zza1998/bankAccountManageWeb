package com.zza.jpaa.controller;


import com.zza.jpaa.annotion.CurrentUser;
import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.constant.enums.MessageTypeEnum;
import com.zza.jpaa.entity.User;
import com.zza.jpaa.entity.dto.UserInfo;
import com.zza.jpaa.entity.vo.MessageVo;
import com.zza.jpaa.services.MessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/msg")
public class MessageController {

    @Resource
    MessageService messageService;



    @PostMapping("/send")
    public ResultData sendMessage(@RequestBody MessageVo messageVo, @CurrentUser UserInfo user) {
        messageService.send(user.getUserId(), messageVo.getUsers(), messageVo.getMsg(), MessageTypeEnum.NORMAL);
        return ResultData.success("消息发送成功");
    }
}

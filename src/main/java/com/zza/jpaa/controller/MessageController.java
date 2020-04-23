package com.zza.jpaa.controller;


import com.zza.jpaa.annotion.CurrentUser;
import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.constant.enums.MessageTypeEnum;
import com.zza.jpaa.entity.Message;
import com.zza.jpaa.entity.dto.MessageDto;
import com.zza.jpaa.entity.dto.UserInfo;
import com.zza.jpaa.entity.vo.MessageVo;
import com.zza.jpaa.services.MessageService;
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
}

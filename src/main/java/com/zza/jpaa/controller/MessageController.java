package com.zza.jpaa.controller;


import com.zza.jpaa.annotion.CurrentUser;
import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.constant.enums.MessageTypeEnum;
import com.zza.jpaa.entity.Message;
import com.zza.jpaa.entity.dto.UserInfo;
import com.zza.jpaa.entity.vo.MessageVo;
import com.zza.jpaa.services.MessageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/msg")
public class MessageController {

//    @Resource
//    MessageService messageService;
//
//
//
//    @PostMapping("/send")
//    public ResultData sendMessage(@RequestBody MessageVo messageVo, @CurrentUser UserInfo user) {
//        messageService.send(user.getUserId(), messageVo.getUsers(), messageVo.getMsg(), MessageTypeEnum.NORMAL);
//        return ResultData.success("消息发送成功");
//    }
//
//    @GetMapping("/getMessage")
//    public ResultData getMessages(@CurrentUser UserInfo user){
//       List<Message> messages = messageService.getUserMessages(user.getUserId());
//       return ResultData.success("获取成功",messages);
//    }
}

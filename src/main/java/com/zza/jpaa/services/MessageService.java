package com.zza.jpaa.services;

import com.zza.jpaa.constant.enums.MessageTypeEnum;
import com.zza.jpaa.entity.Message;
import com.zza.jpaa.entity.dto.MessageDto;

import java.util.List;

public interface MessageService {

    /**
     * 管理员发送消息
     * @param userId 管理员id
     * @param title 消息标题
     * @param content 消息内容
     */
    void send(String userId, String title, String content);

    /**
     * 获取发送的消息
     * @param userId 用户id
     * @param isAdmin 是否管理员
     * @return
     */
    List<MessageDto> getUserMessages(String userId, boolean isAdmin);


    void delMessage(Integer id);
}

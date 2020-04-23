package com.zza.jpaa.services;

import com.zza.jpaa.constant.enums.MessageTypeEnum;
import com.zza.jpaa.entity.Message;
import com.zza.jpaa.entity.dto.MessageDto;

import java.util.List;

public interface MessageService {
    void send(String userId, List<String> sendToId, String msg, MessageTypeEnum type);

    List<MessageDto> getUserMessages(String userId, boolean isAdmin);


}

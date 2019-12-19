package com.zza.jpaa.services;

import com.zza.jpaa.constant.enums.MessageTypeEnum;

import java.util.List;

public interface MessageService {
    void send(String userId, List<String> sendToId, String msg, MessageTypeEnum type);
}

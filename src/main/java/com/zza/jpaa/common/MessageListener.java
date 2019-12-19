package com.zza.jpaa.common;


import com.zza.jpaa.annotion.CurrentUser;
import com.zza.jpaa.constant.enums.MessageTypeEnum;
import com.zza.jpaa.entity.Message;
import com.zza.jpaa.entity.User;
import com.zza.jpaa.respository.MessageRepository;
import com.zza.jpaa.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class MessageListener {

    @Resource
    AuthService authService;

    @Resource
    MessageRepository messageRepository;

    @KafkaListener(topics = "myMsg")
    public void processMessage(ConsumerRecord<String, String> content) {
        User myUser;
        if ((myUser = authService.getCurrUser()) == null) {
            log.error("用户未登录！");
            return;
        }
        if (content.key().equals(myUser.getId())) {
            messageRepository.save(Message.builder()
                    .msg(content.value())
                    .userId(content.key())
                    .sendId(authService.getCurrUser().getId())
                    .type(MessageTypeEnum.NORMAL.getCode())
                    .build());
            log.info("用户 {} 收到消息 {} 来自 {} ", myUser.getId(),content.value(),content.key());
        }
    }
}

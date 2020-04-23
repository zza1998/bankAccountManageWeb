package com.zza.jpaa.services.impl;

import com.zza.jpaa.annotion.CurrentUser;
import com.zza.jpaa.constant.enums.MessageTypeEnum;
import com.zza.jpaa.entity.Message;
import com.zza.jpaa.entity.User;
import com.zza.jpaa.entity.dto.MessageDto;
import com.zza.jpaa.entity.dto.UserInfoDto;
import com.zza.jpaa.exception.BizException;
import com.zza.jpaa.respository.MessageRepository;
import com.zza.jpaa.respository.UserRepository;
import com.zza.jpaa.services.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    MessageRepository messageRepository;

    @Resource
    UserRepository userRepository;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Override
    public void send(String userId, List<String> sendToId, String msg, MessageTypeEnum type) {

    }

    @Override
    public List<MessageDto> getUserMessages(String userId, boolean isAdmin) {
        List<Message> messageList = messageRepository.findAll();
        if (!isAdmin)
            return messageList
                    .stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());

        return messageList
                .stream()
                .filter((o) -> o.getUserId().equals(userId))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private MessageDto mapToDto(Message message){
        Optional<User> byId = userRepository.findById(message.getUserId());
//        if (!byId.isPresent()){
//            throw new BizException("用户不存在");
//        }
        String name = byId.get().getName();
        return MessageDto.builder()
                .id(message.getId())
                .msg(message.getMsg())
                .status(message.getStatus())
                .title(message.getTitle())
                .userName(name)
                .createTime(sdf.format(message.getCreateTime()))
                .build();

    }
}

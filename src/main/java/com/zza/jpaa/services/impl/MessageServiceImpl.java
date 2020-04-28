package com.zza.jpaa.services.impl;

import com.zza.jpaa.annotion.CurrentUser;
import com.zza.jpaa.constant.enums.MessageTypeEnum;
import com.zza.jpaa.constant.enums.OperaTypeEnum;
import com.zza.jpaa.entity.Message;
import com.zza.jpaa.entity.User;
import com.zza.jpaa.entity.dto.MessageDto;
import com.zza.jpaa.exception.BizException;
import com.zza.jpaa.respository.MessageRepository;
import com.zza.jpaa.respository.UserRepository;
import com.zza.jpaa.services.LogService;
import com.zza.jpaa.services.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Resource
    MessageRepository messageRepository;

    @Resource
    UserRepository userRepository;

    @Resource
    RedisTemplate<String,Object> redisTemplate;

    @Resource
    LogService logService;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private static final String SEND_MESSAGE_KEY = "message:send:userId:%s";

    @Override
    public void send(String userId, String title, String content) {
        if(StringUtils.isEmpty(title) || StringUtils.isEmpty(content)){
            throw new BizException("消息内容不完整");
        }
        Boolean sendFlag = redisTemplate.opsForValue()
                .setIfAbsent(String.format(SEND_MESSAGE_KEY, userId), title, 10L, TimeUnit.MINUTES);
        if (sendFlag != null && sendFlag){
            try {
                Message message = Message.builder()
                        .msg(content)
                        .title(title)
                        .createTime(new Date())
                        .status(1)
                        .updateTime(new Date())
                        .userId(userId)
                        .build();
                messageRepository.save(message);
                logService.doLog(OperaTypeEnum.SEND_MESSAGE);
                return;
            }catch (Exception e){
                log.error("消息发送失败",e);
                throw new BizException("消息发送失败");
            }finally {
                redisTemplate.delete(String.format(SEND_MESSAGE_KEY,userId));
            }
        }
        throw new BizException("您的消息发送过于频繁，请稍后再试");
    }

    @Override
    public List<MessageDto> getUserMessages(String userId, boolean isAdmin) {
        List<Message> messageList = messageRepository.findAll();
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()){
            throw new BizException("用户不存在");
        }
        if (!isAdmin)
            return messageList
                    .stream()
                    .filter((o) -> user.get().getCreateTime().compareTo(o.getCreateTime())<=0)
                    .map(this::mapToDto)
                    .collect(Collectors.toList());


        return messageList
                .stream()
                .filter((o) -> o.getUserId().equals(userId))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delMessage(Integer id) {
        messageRepository.deleteById(id);
        logService.doLog(OperaTypeEnum.DELETE_MESSAGE);
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

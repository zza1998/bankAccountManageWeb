//package com.zza.jpaa.services.impl;
//
//import com.zza.jpaa.constant.enums.MessageTypeEnum;
//import com.zza.jpaa.entity.Message;
//import com.zza.jpaa.exception.BizException;
//import com.zza.jpaa.respository.MessageRepository;
//import com.zza.jpaa.services.MessageService;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//
//@Service
//public class MessageServiceImpl implements MessageService {
//
//    @Resource
//    MessageRepository messageRepository;
//
//    @Resource
//    KafkaTemplate<String,Object> kafkaTemplate;
//
////    @Value("${spring.kafka.topic}")
////    public String topic;
//
////    @Override
////    @Transactional
////    public void send(String userId, List<String> sendToId, String msg, MessageTypeEnum type) {
////        if (sendToId.isEmpty()){
////            throw new BizException("发送人为空");
////        }
////        for (String uid:sendToId){
////            kafkaTemplate.send(topic,uid,msg);
////        }
////    }
//
//    @Override
//    public List<Message> getUserMessages(String userId) {
//        return messageRepository.findAllBySendId(userId);
//    }
//}

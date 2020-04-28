package com.zza.jpaa.services.impl;

import com.google.common.collect.Lists;
import com.zza.jpaa.config.UserInfoConfig;
import com.zza.jpaa.constant.enums.OperaTypeEnum;
import com.zza.jpaa.entity.OperatorLog;
import com.zza.jpaa.entity.User;
import com.zza.jpaa.entity.dto.LogDto;
import com.zza.jpaa.respository.LogRepository;
import com.zza.jpaa.respository.UserRepository;
import com.zza.jpaa.services.LogService;
import com.zza.jpaa.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

@Service
public class LogServiceImpl implements LogService {

    @Resource
    LogRepository logRepository;

    @Resource
    UserRepository userRepository;
    @Override
    public void doLog(OperaTypeEnum operaType, String userId, BigDecimal number) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()){
            return;
        }
        User user = optionalUser.get();
        OperatorLog log = OperatorLog.builder()
                .number(number)
                .operaType(operaType.getCode())
                .operaUserId(userId)
                .operaUserName(user.getName())
                .createTime(new Date())
                .build();
        logRepository.save(log);
    }

    @Override
    public void doLog(OperaTypeEnum operaType, String userId) {
        doLog(operaType,userId,null);
    }

    @Override
    public void doLog(OperaTypeEnum operaType) {
        doLog(operaType, UserInfoConfig.currUserInfos.get().getUserId());
    }

    @Override
    public List<LogDto> getLogList() {
        List<LogDto> result = Lists.newArrayList();
        logRepository.findAll().forEach((o-> {
            LogDto t = new LogDto();
            BeanUtils.copyProperties(o,t);
            t.setOperaTypeName(OperaTypeEnum.getTypeByCode(t.getOperaType()));
            t.setCreateTime(DateUtil.format(o.getCreateTime()));
            result.add(t);
        }));

        return result;
    }

}

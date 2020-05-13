package com.zza.jpaa.services.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.config.UserInfoConfig;
import com.zza.jpaa.constant.enums.OperaTypeEnum;
import com.zza.jpaa.entity.OperatorLog;
import com.zza.jpaa.entity.User;
import com.zza.jpaa.entity.dto.LogDto;
import com.zza.jpaa.entity.dto.LogListDto;
import com.zza.jpaa.entity.dto.LogTypeDto;
import com.zza.jpaa.entity.vo.LogListVo;
import com.zza.jpaa.respository.LogRepository;
import com.zza.jpaa.respository.UserRepository;
import com.zza.jpaa.respository.impl.LogRepositoryImpl;
import com.zza.jpaa.services.LogService;
import com.zza.jpaa.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
public class LogServiceImpl implements LogService {

    @Resource
    LogRepository logRepository;

    @Resource
    UserRepository userRepository;

    @Resource
    LogRepositoryImpl logRepositoryImpl;
    @Override
    public void doLog(OperaTypeEnum operaType, String userId, BigDecimal number) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
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
        doLog(operaType, userId, null);
    }

    @Override
    public void doLog(OperaTypeEnum operaType) {
        doLog(operaType, UserInfoConfig.currUserInfos.get().getUserId());
    }


    @Override
    public void delLog(String id) {
        synchronized (this) {
            Optional<OperatorLog> byId = logRepository.findById(id);
            if (byId.isPresent()) {
                logRepository.deleteById(id);
            }
        }
    }

    @Override
    public ResultData getTpe() {
        List<LogTypeDto> types = Lists.newArrayList();
        OperaTypeEnum[] values = OperaTypeEnum.values();
        for (OperaTypeEnum e:values) {
            types.add(new LogTypeDto(e.getCode(),e.getType()));
        }
        return ResultData.success("ok",types);
    }

    @Override
    public LogListDto getLogList(LogListVo logListVo) {
        List<LogDto> result = new ArrayList<>();
        LogListDto logListDto = new LogListDto();
        List<HashMap> history = logRepositoryImpl.findHistory(logListVo);
        if (CollectionUtils.isEmpty(history)){
            logListDto.setData(result);
            logListDto.setTotal(0);
            return logListDto;
        }
        int size = history.size();
        int pindex = logListVo.getPageIndex();
        int psize =logListVo.getPageSize();
        history = history.subList(Math.max(0,pindex*psize),Math.min(size,(pindex+1)*psize));
        history.forEach(o -> {
            LogDto t = new LogDto();
            t.setOperaType((Integer) o.get("opera_type"));
            t.setId((String)o.get("id"));
            t.setNumber((BigDecimal) o.get("number"));
            t.setOperaTypeName(OperaTypeEnum.getTypeByCode((Integer) o.get("opera_type")));
            t.setOperaUserName((String) o.get("opera_username"));
            t.setCreateTime(DateUtil.format((Date) o.get("create_time")));
            result.add(t);
        });
        logListDto.setData(result);
        logListDto.setTotal(size);
        return logListDto;
    }

}

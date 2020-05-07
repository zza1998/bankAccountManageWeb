package com.zza.jpaa.services;


import com.zza.jpaa.constant.enums.OperaTypeEnum;
import com.zza.jpaa.entity.dto.LogDto;
import com.zza.jpaa.entity.dto.LogListDto;

import java.math.BigDecimal;
import java.util.List;


public interface LogService {

    void doLog(OperaTypeEnum operaType, String userId, BigDecimal number);

    void doLog(OperaTypeEnum operaType, String userId);

    void doLog(OperaTypeEnum operaType);

    LogListDto getLogList(Integer pageNum, Integer pageSize);

    void delLog(String id);
}

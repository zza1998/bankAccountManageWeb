package com.zza.jpaa.services;


import com.zza.jpaa.constant.enums.OperaTypeEnum;
import com.zza.jpaa.entity.OperatorLog;
import com.zza.jpaa.entity.dto.LogDto;

import java.math.BigDecimal;
import java.util.List;


public interface LogService {

    void doLog(OperaTypeEnum operaType, String userId, BigDecimal number);

    void doLog(OperaTypeEnum operaType, String userId);

    void doLog(OperaTypeEnum operaType);

    List<LogDto> getLogList();
}

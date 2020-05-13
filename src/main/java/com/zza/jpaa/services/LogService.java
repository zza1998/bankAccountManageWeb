package com.zza.jpaa.services;


import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.constant.enums.OperaTypeEnum;
import com.zza.jpaa.entity.dto.LogDto;
import com.zza.jpaa.entity.dto.LogListDto;
import com.zza.jpaa.entity.vo.LogListVo;

import java.math.BigDecimal;
import java.util.List;


public interface LogService {

    void doLog(OperaTypeEnum operaType, String userId, BigDecimal number);

    void doLog(OperaTypeEnum operaType, String userId);

    void doLog(OperaTypeEnum operaType);

    void delLog(String id);

    ResultData getTpe();

    LogListDto getLogList(LogListVo logListVo);
}

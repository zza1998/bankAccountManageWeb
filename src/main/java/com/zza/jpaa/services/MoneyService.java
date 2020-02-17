package com.zza.jpaa.services;

import com.zza.jpaa.entity.dto.BalanceDto;
import com.zza.jpaa.entity.vo.SaveMoneyVo;

import java.math.BigDecimal;
import java.util.List;

public interface MoneyService {
     String saveMoney(SaveMoneyVo saveMoneyVo,  String userId);

     List<BalanceDto> getBalance(String userId);
}

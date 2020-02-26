package com.zza.jpaa.services;

import com.zza.jpaa.entity.dto.BalanceDto;
import com.zza.jpaa.entity.vo.SaveMoneyVo;

public interface MoneyService {
     String saveMoney(SaveMoneyVo saveMoneyVo,  String userId);

     BalanceDto getBalance(String userId);

     String reduceMoney(SaveMoneyVo saveMoneyVo, String userId);
}

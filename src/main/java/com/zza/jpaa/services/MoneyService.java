package com.zza.jpaa.services;

import com.zza.jpaa.entity.dto.BalanceDto;

import java.math.BigDecimal;
import java.util.List;

public interface MoneyService {
     BigDecimal saveMoney(Integer num, String userId, String bankId);

     List<BalanceDto> getBalance(String userId);
}

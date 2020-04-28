package com.zza.jpaa.services;

import com.zza.jpaa.entity.dto.StatisticAccountByBankDto;

import java.util.List;

public interface StatisticService {

    /**
     * 统计平台中所有银行对应的账户数
     * @return
     */
    List<StatisticAccountByBankDto> statisticAccountByBank();
}

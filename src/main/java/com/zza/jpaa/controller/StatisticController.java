package com.zza.jpaa.controller;

import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.entity.dto.StatisticAccountByBankDto;
import com.zza.jpaa.respository.impl.BankAccountRepositoryImpl;
import com.zza.jpaa.services.StatisticService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/statistic")
public class StatisticController {

    @Resource
    StatisticService statisticService;

    @PostMapping("/account/bank")
    public ResultData statisticAccountByBank(){
        List<StatisticAccountByBankDto> statisticAccountByBankDtos = statisticService.statisticAccountByBank();
        return ResultData.success("查询成功",statisticAccountByBankDtos);
    }
}

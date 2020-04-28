package com.zza.jpaa.services.impl;

import com.zza.jpaa.entity.dto.StatisticAccountByBankDto;
import com.zza.jpaa.respository.BankAccountRepository;
import com.zza.jpaa.respository.impl.BankAccountRepositoryImpl;
import com.zza.jpaa.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    BankAccountRepositoryImpl bankAccountRepository;

    @Override
    public List<StatisticAccountByBankDto> statisticAccountByBank() {
        return bankAccountRepository.findAccountByBank();
    }
}

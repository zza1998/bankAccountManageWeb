package com.zza.jpaa.services.impl;

import com.zza.jpaa.entity.BankAccount;
import com.zza.jpaa.entity.dto.BalanceDto;
import com.zza.jpaa.exception.BizCode;
import com.zza.jpaa.exception.BizException;
import com.zza.jpaa.respository.BankAccountRepository;
import com.zza.jpaa.respository.impl.BalanceRepository;
import com.zza.jpaa.services.MoneyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class MoneyServiceImpl implements MoneyService {

    @Resource
    BankAccountRepository bankAccountRepository;

    @Resource
    BalanceRepository balanceRepository;
    @Override
    @Transactional
    public BigDecimal saveMoney(final Integer num, String userId, String bankId) {
       BankAccount account = bankAccountRepository.findBankAccountByUserIdAndBankId(userId,bankId);
       if (account == null){
            throw new BizException(BizCode.BANK_ACCOUNT_NOT_FIND);
       }
       bankAccountRepository.addBalance(num,account.getId());
       return bankAccountRepository.findBankAccountByUserIdAndBankId(userId,bankId).getBalance();
    }

    @Override
    public List<BalanceDto> getBalance(String userId) {
        List<BalanceDto> result =  balanceRepository.getBalance(userId);
        return result;
    }
}

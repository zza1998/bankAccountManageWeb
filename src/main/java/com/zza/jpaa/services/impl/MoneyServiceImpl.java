package com.zza.jpaa.services.impl;

import com.zza.jpaa.entity.BankAccount;
import com.zza.jpaa.entity.dto.BalanceDto;
import com.zza.jpaa.entity.vo.SaveMoneyVo;
import com.zza.jpaa.exception.BizCode;
import com.zza.jpaa.exception.BizException;
import com.zza.jpaa.respository.BankAccountRepository;
import com.zza.jpaa.services.MoneyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MoneyServiceImpl implements MoneyService {

    @Resource
    BankAccountRepository bankAccountRepository;


    @Override
    @Transactional
    public String saveMoney(SaveMoneyVo saveMoneyVo, String operateId) {
        Optional<BankAccount> account = bankAccountRepository.findByCardId(saveMoneyVo.getCardId());
        if (!account.isPresent()){
            throw new BizException(BizCode.BANK_ACCOUNT_NOT_FIND);
        }
        bankAccountRepository.addBalance(saveMoneyVo.getNum(),saveMoneyVo.getCardId());
        return "success";
    }

    @Override
    public List<BalanceDto> getBalance(String userId) {

        return null;
    }
}

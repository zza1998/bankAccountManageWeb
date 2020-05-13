package com.zza.jpaa.services.impl;

import com.zza.jpaa.config.UserInfoConfig;
import com.zza.jpaa.constant.enums.OperaTypeEnum;
import com.zza.jpaa.entity.BankAccount;
import com.zza.jpaa.entity.dto.BalanceDto;
import com.zza.jpaa.entity.vo.SaveMoneyVo;
import com.zza.jpaa.exception.BizCode;
import com.zza.jpaa.exception.BizException;
import com.zza.jpaa.respository.BankAccountRepository;
import com.zza.jpaa.services.LogService;
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

    @Resource
    LogService logService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveMoney(SaveMoneyVo saveMoneyVo, String operateId) {
        Optional<BankAccount> account = bankAccountRepository.findByCardId(saveMoneyVo.getCardId());
        if (!account.isPresent()){
            throw new BizException(BizCode.BANK_ACCOUNT_NOT_FIND);
        }
        bankAccountRepository.addBalance(saveMoneyVo.getNum(),saveMoneyVo.getCardId());
        logService.doLog(OperaTypeEnum.SAVE_MONEY, UserInfoConfig.currUserInfos.get().getUserId(),new BigDecimal(saveMoneyVo.getNum()));
        return "success";
    }

    @Override
    public BalanceDto getBalance(String id) {
        Optional<BankAccount> byId = bankAccountRepository.findByCardId(id);
        if (!byId.isPresent()){
            throw new BizException("账户不存在");
        }
        BankAccount account = byId.get();
        return new BalanceDto(account.getBalance(),account.getBankCode().getBankName());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String reduceMoney(SaveMoneyVo saveMoneyVo, String operateId) {
        Optional<BankAccount> account =bankAccountRepository.findByCardId(saveMoneyVo.getCardId());
        if (!account.isPresent()){
            throw new BizException(BizCode.BANK_ACCOUNT_NOT_FIND);
        }
        if (account.get().getBalance().compareTo(BigDecimal.valueOf(saveMoneyVo.getNum())) < 0){
            throw new BizException("账户余额不足");
        }
        bankAccountRepository.reduceBalance(saveMoneyVo.getNum(),saveMoneyVo.getCardId());
        logService.doLog(OperaTypeEnum.REDUCE_MONEY,UserInfoConfig.currUserInfos.get().getUserId(),new BigDecimal(saveMoneyVo.getNum()));
        return "success";
    }
}

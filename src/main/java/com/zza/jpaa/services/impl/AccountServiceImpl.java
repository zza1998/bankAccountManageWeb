package com.zza.jpaa.services.impl;

import com.zza.jpaa.config.UserInfoConfig;
import com.zza.jpaa.constant.enums.OperaTypeEnum;
import com.zza.jpaa.entity.BankAccount;
import com.zza.jpaa.entity.User;
import com.zza.jpaa.entity.dto.AccountDto;
import com.zza.jpaa.entity.dto.PageDto;
import com.zza.jpaa.entity.dto.UserInfo;
import com.zza.jpaa.entity.vo.AccountVo;
import com.zza.jpaa.entity.vo.PageSearchVo;
import com.zza.jpaa.enums.BankEnum;
import com.zza.jpaa.exception.BizException;
import com.zza.jpaa.respository.BankAccountRepository;
import com.zza.jpaa.respository.UserRepository;
import com.zza.jpaa.services.AccountService;
import com.zza.jpaa.services.LogService;
import com.zza.jpaa.util.IdCardUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    BankAccountRepository bankAccountRepository;

    @Resource
    UserRepository userRepository;

    @Resource
    LogService logService;


    @Override
    @Transactional
    public void createAccount(AccountVo accountVo, UserInfo userInfo) {
        if (!IdCardUtil.isValidate18Idcard(accountVo.getIdCard())) {
            throw new BizException("【创建账户流程】身份证信息校验失败");
        }
        List<BankAccount> allAccount = bankAccountRepository.findAllList();
        allAccount.forEach((o) -> {
            if (o.getIdCard().equals(accountVo.getIdCard())
                    && o.getBankCode().getBankCode().equals(accountVo.getBankCode())
                    && o.getCardId().equals(accountVo.getCardId())) {
                throw new BizException("【创建账户流程】 账户重复 添加失败");
            }
        });
        long count = allAccount.stream()
                .filter(bankAccount -> bankAccount.getIdCard().equals(accountVo.getIdCard())).count();
        if (count > 10) {
            throw new BizException("【创建账户流程】 用户账户数量达到上限");
        }
        BankAccount account = BankAccount.builder()
                .idCard(accountVo.getIdCard())
                .userName(accountVo.getUserName())
                .balance(new BigDecimal(0))
                .cardId(accountVo.getCardId())
                .bankCode(BankEnum.getBankByCode(accountVo.getBankCode()))
                .operateId(userInfo.getUserId())
                .phone(accountVo.getPhone())
                .status(0)
                .build();

        bankAccountRepository.save(account);
        logService.doLog(OperaTypeEnum.CREATE_ACCOUNT,userInfo.getUserId(),null);
    }

    @Override
    public PageDto<AccountDto> getList(PageSearchVo pageSearchVo) {
        if (null == pageSearchVo.getPageIndex() || null == pageSearchVo.getPageSize()) {
            throw new BizException("【获取账户列表】页码参数错误");
        }
        List<BankAccount> accountList = bankAccountRepository.findAllList();
        List<User> users = userRepository.findAll();
        List<AccountDto> accountDtos = new ArrayList<>();
        PageDto<AccountDto> pageDto = new PageDto<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        accountList.forEach((o) -> {
            String operateName = users.stream()
                    .filter((u) -> u.getId().equals(o.getOperateId()))
                    .findAny()
                    .orElse(User.builder().name("未知").build())
                    .getName();
            accountDtos.add(AccountDto.builder()
                    .bankName(o.getBankCode().getBankName())
                    .id(o.getId())
                    .phone(o.getPhone())
                    .cardId(o.getCardId())
                    .userName(o.getUserName())
                    .createTime(o.getCreateTime() == null ? "未知" : sdf.format(o.getCreateTime()))
                    .operateName(operateName)
                    .status(o.getStatus())
                    .build()
            );
        });
        // 筛选查询
        List<AccountDto> resultList = accountDtos;
        if (pageSearchVo.getFilter() != null && !pageSearchVo.getFilter().equals("")) {
            String filter = pageSearchVo.getFilter();
            resultList = accountDtos.stream()
                    .filter(accountDto ->
                            accountDto.getBankName().contains(filter)
                                    || accountDto.getCardId().contains(filter)
                                    || accountDto.getUserName().contains(filter)
                                    || accountDto.getOperateName().contains(filter)
                                    || accountDto.getPhone().contains(filter))
                    .collect(Collectors.toList());
        }
        if (pageSearchVo.getSort() != null && !pageSearchVo.getSort().equals("")) {
            if (pageSearchVo.getSort().equals("+createTime")) {
                resultList = resultList.stream().sorted((Comparator.comparing(AccountDto::getCreateTime))).
                        collect(Collectors.toList());
            } else if (pageSearchVo.getSort().equals("-createTime")) {
                resultList = resultList.stream().sorted((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime())).
                        collect(Collectors.toList());
            }

        }
        // 分页
        pageDto.setCurrPage(pageSearchVo.getPageIndex());
        pageDto.setTotalCount(resultList.size());
        pageDto.setPageSize(pageSearchVo.getPageSize());
        pageDto.setListData(resultList.subList
                (Math.max((pageSearchVo.getPageIndex() - 1) * pageSearchVo.getPageSize(), 0),
                        Math.min((pageSearchVo.getPageIndex()) * pageSearchVo.getPageSize(), resultList.size())
                )
        );
        return pageDto;
    }

    @Override
    public void deleteAccount(String id) {
        bankAccountRepository.deleteById(id);
    }

    @Override
    public void changeAccount(String id, Integer status) {
        Optional<BankAccount> account = bankAccountRepository.findById(id);
        if (!account.isPresent()||account.get().getStatus().equals(1)) {
            throw new BizException("账户不存在");
        }

        BankAccount bankAccount = account.get();
        bankAccount.setStatus(status);
        bankAccountRepository.save(bankAccount);
        if (status.equals(1))
            logService.doLog(OperaTypeEnum.DELETE_ACCOUNT, UserInfoConfig.currUserInfos.get().getUserId(),null);
        if (status.equals(0))
            logService.doLog(OperaTypeEnum.UNFREEZE_ACCOUNT, UserInfoConfig.currUserInfos.get().getUserId(),null);
        if (status.equals(2))
            logService.doLog(OperaTypeEnum.FROZEN_ACCOUNT, UserInfoConfig.currUserInfos.get().getUserId(),null);
    }
}

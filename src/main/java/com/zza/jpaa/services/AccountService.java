package com.zza.jpaa.services;

import com.zza.jpaa.entity.dto.AccountDto;
import com.zza.jpaa.entity.dto.PageDto;
import com.zza.jpaa.entity.dto.UserInfo;
import com.zza.jpaa.entity.vo.AccountVo;
import com.zza.jpaa.entity.vo.PageSearchVo;

import java.util.List;

public interface AccountService {

    void createAccount(AccountVo accountVo, UserInfo userInfo);

    PageDto<AccountDto> getList(PageSearchVo pageSearchVo);

    void deleteAccount(String id);

    void changeAccount(String id, Integer status);
}

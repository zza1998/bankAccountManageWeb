package com.zza.jpaa.services;

import com.zza.jpaa.entity.dto.AccountDto;
import com.zza.jpaa.entity.dto.PageDto;
import com.zza.jpaa.entity.dto.UserInfo;
import com.zza.jpaa.entity.vo.AccountVo;
import com.zza.jpaa.entity.vo.PageSearchVo;

import java.util.List;

public interface AccountService {

    /**
     * 创建账户
     * @param accountVo 账户参数模型
     * @param userInfo 用户信息模型
     */
    void createAccount(AccountVo accountVo, UserInfo userInfo);

    /**
     * 获取用户列表
     * @param pageSearchVo 页面搜索参数模型
     * @return 用户列表
     */
    PageDto<AccountDto> getList(PageSearchVo pageSearchVo);

    void deleteAccount(String id);

    void changeAccount(String id, Integer status);
}

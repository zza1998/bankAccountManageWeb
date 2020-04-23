package com.zza.jpaa.services;


import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.entity.dto.InvitedCodeDto;

import java.util.List;

public interface InvitedCodeService {

    /**
     * 创建随机生成邀请码
     * @param account 邀请码的账户
     * @return
     */
    InvitedCodeDto createCode(String account);

    /**
     * 删除邀请码
     * @param id 邀请码id
     * @return
     */
    ResultData deleteCode(String id);

    /**
     * 获取邀请码详情列表
     * @return
     */
    List<InvitedCodeDto> codeList();
}

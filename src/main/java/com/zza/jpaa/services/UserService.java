package com.zza.jpaa.services;

import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.entity.dto.UserInfoDto;
import com.zza.jpaa.entity.vo.ModifyUserVo;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserService {

    /**
     * 获取用户信息权限
     * @param userId 用户唯一表示 ID
     * @return 用户信息
     */
    UserInfoDto getInfo(String userId);

    /**
     * 修改用户部分基本信息
     * @param modifyUserVo 修改模型
     * @return
     */
    ResultData modifyUserInfo(ModifyUserVo modifyUserVo);
}

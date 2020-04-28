package com.zza.jpaa.services;

import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.entity.User;
import com.zza.jpaa.entity.vo.RegisterVo;

public interface AuthService {

    ResultData login (String userName, String password);

    ResultData register(RegisterVo registerVo);

}

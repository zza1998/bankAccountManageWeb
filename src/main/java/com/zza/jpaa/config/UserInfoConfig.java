package com.zza.jpaa.config;


import com.zza.jpaa.entity.dto.UserInfo;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UserInfoConfig {


    public static ThreadLocal<UserInfo> currUserInfos = new ThreadLocal<>();


}

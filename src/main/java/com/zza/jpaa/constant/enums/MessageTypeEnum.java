package com.zza.jpaa.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum  MessageTypeEnum {
    NORMAL("1","普通消息"),
    USER_MESSAGE("2","用户信息"),
    SYSTEM_MESSAGE("0","系统信息");


    private String code;

    private String type;
}

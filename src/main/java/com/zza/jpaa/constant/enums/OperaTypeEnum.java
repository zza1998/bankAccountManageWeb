package com.zza.jpaa.constant.enums;


import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public enum OperaTypeEnum {
    SAVE_MONEY(33001, "存钱"),
    REDUCE_MONEY(33002, "取钱"),
    SEND_MESSAGE(33003, "发送消息"),
    DELETE_MESSAGE(33004, "删除消息"),
    CREATE_ACCOUNT(33005, "创建账户"),
    DELETE_ACCOUNT(33006, "删除账户"),
    FROZEN_ACCOUNT(33007, "冻结账户"),
    UNFREEZE_ACCOUNT(33008, "解冻账户"),
    LOGIN(33009, "登录系统"),
    LOGOUT(33010, "登出账户"),
    CREATE_CODE(33011, "创建邀请码"),
    DEL_CODE(33012, "删除邀请码");


    private Integer code;

    private String type;

    private static Map<Integer, String> logMap = Maps.newHashMap();

    static {
        for (OperaTypeEnum e : OperaTypeEnum.values()) {
            logMap.put(e.code, e.type);
        }
    }

    public static String getTypeByCode(Integer code) {
        return logMap.get(code);
    }
}

package com.zza.jpaa.exception;

public enum BizCode {

    // 用户登录
    USER_MSG_NULL(10001,"未输入完整信息"),
    USER_NOT_EXIST(10002,"用户不存在"),
    USER_PASSWORD_ERROR(10003,"密码错误"),
    USER_LOGIN_ERROR(10004,"密码错误此数过多，稍后再试"),
    BANK_ACCOUNT_NOT_FIND(10001,"银行账户不存在");


    public final int code;
    public final String message;

    BizCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

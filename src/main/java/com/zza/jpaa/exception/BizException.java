package com.zza.jpaa.exception;

public class BizException extends RuntimeException {
    static final long serialVersionUID = -7034897190745766988L;

    private int code;
    private String msg;
    private Object data;
    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message) {
        super(message);
        msg = message;
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(BizCode bizCode) {
        msg = bizCode.message;
        code = bizCode.code;
    }

    public BizException(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public BizException(BizCode bizCode,Object data) {
        msg = bizCode.message;
        code = bizCode.code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data){
        this.data = data;
    }

    public Object getData(){
        return data;
    }

    public String toString() {
        String s = this.getClass().getName() + ", code:" + this.code + (this.msg == null ? "" : ",msg:" + this.msg);
        String message = this.getLocalizedMessage();
        return message != null ? s + ": " + message : s;
    }
}
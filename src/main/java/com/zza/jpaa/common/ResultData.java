package com.zza.jpaa.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ResultData<DATA>  {
    private final static Integer SUCCESS = 200;
    private final static Integer NOT_FIND = 404;
    private final static Integer FAILURE = 10000;
    private final static ResultData dao = new ResultData();

    private int code;
    private DATA data;
    private String msg;

    public ResultData(int code, DATA data) {
        this.code = code;
        this.data = data;
    }

    public ResultData() {

    }

    public static ResultData success() {
        dao.data = null;
        dao.code = SUCCESS;
        return dao;
    }
    public static ResultData success(String msg) {
        dao.data = null;
        dao.code = SUCCESS;
        dao.msg = msg;
        return dao;
    }

    public static ResultData success(String msg, Object data){
        dao.data = data;
        dao.code = SUCCESS;
        dao.msg = msg;
        return dao;
    }
    public static ResultData fail() {
        dao.data = null;
        dao.code = FAILURE;
        return dao;
    }
    public static ResultData fail(String msg) {
        dao.data = null;
        dao.code = FAILURE;
        dao.msg = msg;
        return dao;
    }
    public ResultData resultData(int code, DATA data) {
        this.code = code;
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}

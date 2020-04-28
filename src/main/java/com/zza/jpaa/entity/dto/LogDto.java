package com.zza.jpaa.entity.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class LogDto {

    private String id;

    private Integer operaType;

    private String operaTypeName;

    private String operaUserId;

    private String operaUserName;

    private BigDecimal number;

    private String createTime;
}

package com.zza.jpaa.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogDto {

    private String id;

    private Integer operaType;

    private String operaTypeName;

    private String operaUserId;

    private String operaUserName;

    private BigDecimal number;

    private String createTime;
}

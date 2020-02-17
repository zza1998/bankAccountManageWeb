package com.zza.jpaa.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private String id;

    private String bankName;

    private String cardId;

    private String phone;

    private String userName;

    private String operateName;

    private Integer status;

    private String createTime;

}

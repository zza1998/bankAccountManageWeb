package com.zza.jpaa.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvitedCodeDto {

    private String id;

    private String code;

    private String account;

    private Integer status; // 0:未使用 1:已使用

    private String createTime;

   // private Date expireTime;// 为 null 表示永久

}

package com.zza.jpaa.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    private Integer id;

    private String msg;

    private String userName;

    private String title;

    private Integer status;

    private String createTime;

    private String updateTime;
}

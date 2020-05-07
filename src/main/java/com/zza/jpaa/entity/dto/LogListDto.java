package com.zza.jpaa.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class LogListDto {

    private List<LogDto> data;

    private Integer total;
}

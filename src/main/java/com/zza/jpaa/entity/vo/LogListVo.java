package com.zza.jpaa.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class LogListVo {
    private Integer pageIndex;
    private Integer pageSize;
    private String rangeKey;
    private String logType;
    private Date startDate;
    private Date endDate;
}

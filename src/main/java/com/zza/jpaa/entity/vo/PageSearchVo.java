package com.zza.jpaa.entity.vo;

import lombok.Data;

@Data
public class PageSearchVo {

    private Integer pageSize;

    private Integer pageIndex;

    private String sort;

    private String filter;

}

package com.zza.jpaa.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDto<T> {
    private List<T> listData; // 当前页数据
    private Integer totalCount; // 总页数
    private Integer currPage; // 当前页
    private Integer pageSize; // 每页大小
}

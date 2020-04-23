package com.zza.jpaa.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {
    ADMIN("管理员", 1),
    EDITOR("编辑人员", 2),
    CUSTOM("顾客", 3),
    USER("会员", 4);
    private String introduce;

    private Integer code;
}

package com.zza.jpaa.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {
    private String userId;
    private String name;
    private String password;
    private Integer role;
    private String idCardNum;
}

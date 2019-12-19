package com.zza.jpaa.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterVo {
    private String userName;
    private String password;
    private String email;
    private String phoneNum;
    private String idCard;
}

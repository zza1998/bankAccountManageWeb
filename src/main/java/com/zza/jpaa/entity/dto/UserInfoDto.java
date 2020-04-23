package com.zza.jpaa.entity.dto;

import com.zza.jpaa.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {
    private String roles;

    private String avatar;

    private String introdution;

    private String name;

}

package com.zza.jpaa.entity.vo;

import com.zza.jpaa.annotion.AdminCheck;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
public class TestVo {

    @AdminCheck(max = 10)
    private Integer workTime;
}

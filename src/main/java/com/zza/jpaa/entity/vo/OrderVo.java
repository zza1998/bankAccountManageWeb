package com.zza.jpaa.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class OrderVo {

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("身份证号")
    private String idcard;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("预约类型")
    private int[] type;

}

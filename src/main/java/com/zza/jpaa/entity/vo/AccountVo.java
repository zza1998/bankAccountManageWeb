package com.zza.jpaa.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class AccountVo {

    @ApiModelProperty("用户身份证号")
    private String idCard;

    @ApiModelProperty("银行code")
    private String bankCode;

    @ApiModelProperty("真实姓名")
    private String userName;

    @ApiModelProperty("卡号id")
    private String cardId;

    @ApiModelProperty("预留手机号")
    private String phone;

    @ApiModelProperty("余额")
    private BigDecimal balance;


}

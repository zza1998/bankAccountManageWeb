package com.zza.jpaa.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class BalanceDto {


    private String bankName;

    private BigDecimal balance;

    public BalanceDto(BigDecimal balance,String bankName) {
        this.bankName = bankName;
        this.balance = balance;
    }
}

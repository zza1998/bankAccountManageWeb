package com.zza.jpaa.entity.dto;

import lombok.Data;


@Data
public class StatisticAccountByBankDto {

    private String name;

    private Integer bankCode;

    private Integer value;

    @Override
    public String toString() {
        return "StatisticAccountByBankDto{" +
                "bankName='" + name + '\'' +
                ", num=" + value +
                '}';
    }
}

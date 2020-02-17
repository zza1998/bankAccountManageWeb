package com.zza.jpaa.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public enum BankEnum {


    GS_BANK("10000","工商银行"),
    NY_BANK("10001","农业银行"),
    TZ_BANK("10002","台州银行");

    private String bankCode;

    private String bankName;

    public static BankEnum getBankByCode(String code){
        for (BankEnum bankEnum : BankEnum.values()){
            if (bankEnum.getBankCode().equals(code)){
                return bankEnum;
            }
        }
        log.info("can't find BankEnum for code {}",code);
        return null;
    }


}

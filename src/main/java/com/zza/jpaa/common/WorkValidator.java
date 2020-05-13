package com.zza.jpaa.common;

import com.zza.jpaa.annotion.AdminCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WorkValidator implements ConstraintValidator<AdminCheck, Integer> {
    private int max;
    private AdminCheck adminCheck;

    @Override
    public void initialize(AdminCheck constraintAnnotation) {
        adminCheck = constraintAnnotation;
        max = adminCheck.max();
    }

    // 因为作用于字段上，所以就一个值进行判断
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null){
            return true;
        }
        adminCheck.message();
        return value<max;
    }
}

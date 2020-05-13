package com.zza.jpaa.annotion;

import com.zza.jpaa.common.WorkValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = {WorkValidator.class})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AdminCheck {
    String message() default "加班时间过长，不能超过{max}小时";

    int max() default 5;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

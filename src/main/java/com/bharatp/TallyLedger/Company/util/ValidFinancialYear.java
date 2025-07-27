package com.bharatp.TallyLedger.Company.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = FinancialYearValidator.class)
public @interface ValidFinancialYear {
    String message() default "Financial year end must be after start";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

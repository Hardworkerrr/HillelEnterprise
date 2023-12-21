package com.hillel.javaee.validation.annotation;

import com.hillel.javaee.validation.validator.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)

public @interface PasswordConstraint {
    int min() default 0;
    int max() default Integer.MAX_VALUE;
    String regexp();

    String message() default "Password is invalid!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

package com.hillel.javaee.validation.validator;

import com.hillel.javaee.validation.annotation.PasswordConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint,String> {
    int min;
    int max;
    String regexp;

    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
        this.regexp = constraintAnnotation.regexp();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.length()>max || s.length()<min || !s.matches(regexp)){
            return false;
        }
        return true;
    }
}

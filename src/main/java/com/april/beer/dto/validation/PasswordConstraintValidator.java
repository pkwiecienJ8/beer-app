package com.april.beer.dto.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword constraint) {
    }

    @Override
    public boolean isValid(String validPassword, ConstraintValidatorContext context) {
       if(validPassword.length() > 5){
          return true;
       }

       context.buildConstraintViolationWithTemplate("Password is too short")
               .addConstraintViolation()
               .disableDefaultConstraintViolation();

       return false;
    }
}

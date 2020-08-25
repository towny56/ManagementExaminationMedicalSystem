package com.fixit.areas.users.models.binding.validations;

import com.fixit.areas.users.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsEmailRegisteredValidator implements ConstraintValidator<IsEmailRegistered,String> {
    @Autowired
    private UsersService userService;

    @Override
    public void initialize(IsEmailRegistered isEmailRegister) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !this.userService.isEmailTaken(s);
    }
}

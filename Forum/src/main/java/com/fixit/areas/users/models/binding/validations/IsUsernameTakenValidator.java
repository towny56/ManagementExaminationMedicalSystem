package com.fixit.areas.users.models.binding.validations;

import com.fixit.areas.users.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsUsernameTakenValidator implements ConstraintValidator<IsUsernameTaken,String> {
    @Autowired
    private UsersService userService;

    @Override
    public void initialize(IsUsernameTaken isUsernameTaken) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !this.userService.isUsernameTaken(s);
    }
}

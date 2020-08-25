package com.fixit.areas.users.models.binding.validations;

import com.fixit.areas.users.models.binding.UsersRegisterBindingModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsPasswordMatchingValidator implements ConstraintValidator<IsPasswordMatching,Object> {
    @Override
    public void initialize(IsPasswordMatching isPasswordMatching) {

    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (object instanceof UsersRegisterBindingModel){
            UsersRegisterBindingModel userModel = (UsersRegisterBindingModel) object;
            return userModel.getPassword().equals(userModel.getConfirmPassword());
        }

        return false;
    }
}

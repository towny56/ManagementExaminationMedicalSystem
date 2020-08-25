package com.fixit.areas.users.models.binding.validations;
import com.fixit.areas.ward.services.WardService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsWardPresentValidator implements ConstraintValidator<IsWardPresent,String> {
    @Autowired
    private WardService wardService;

    @Override
    public void initialize(IsWardPresent isWardPresent) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null)
        {
            return true;
        }
        return this.wardService.findAll().stream().map(w->w.getWardName()).anyMatch(str -> str.equals(s));
    }
}

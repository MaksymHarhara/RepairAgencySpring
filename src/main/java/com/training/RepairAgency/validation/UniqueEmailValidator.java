package com.training.RepairAgency.validation;

import com.training.RepairAgency.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private UserService userService;

    public UniqueEmailValidator(UserService userService) {
        this.userService = userService;
    }

    public void initialize(UniqueEmail constraint) {
    }

    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userService.findByEmail(email).isPresent();
    }

}

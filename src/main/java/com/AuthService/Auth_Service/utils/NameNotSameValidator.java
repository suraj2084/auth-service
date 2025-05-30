package com.AuthService.Auth_Service.utils;

import com.AuthService.Auth_Service.Request.SignupRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameNotSameValidator implements ConstraintValidator<NameNotSame, SignupRequest> {

    @Override
    public boolean isValid(SignupRequest request, ConstraintValidatorContext context) {
        if (request.getFirstName() == null || request.getLastName() == null)
            return true;
        return !request.getFirstName().equalsIgnoreCase(request.getLastName());
    }
}
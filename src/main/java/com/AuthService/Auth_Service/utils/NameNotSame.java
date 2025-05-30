package com.AuthService.Auth_Service.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NameNotSameValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface NameNotSame {
    String message() default "First name and last name must not be the same";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

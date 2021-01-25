package com.oktenwebjava.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(
        validatedBy = {UniqueUserNameValidator.class}
)
@Target({ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)


public @interface UniqueUserName {
    String message() default  "Name should be unique ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

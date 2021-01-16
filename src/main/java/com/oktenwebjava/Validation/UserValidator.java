package com.oktenwebjava.Validation;

import com.oktenwebjava.entity.User;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(User.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (StringUtils.isNotBlank(user.getName())) {
            final char firstLetterOfName = user.getName().charAt(0);
            if (!CharUtils.isAsciiAlphaUpper(firstLetterOfName)) {
                errors.rejectValue("name", "name-capital-letter", "Name should start with capital letter");
            }
        }
    }
}

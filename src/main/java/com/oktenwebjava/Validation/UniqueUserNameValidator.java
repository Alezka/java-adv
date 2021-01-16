package com.oktenwebjava.Validation;

import com.oktenwebjava.dao.UserRepository;
import com.oktenwebjava.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

    private UserRepository userRepository;

    @Autowired
    public UniqueUserNameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UniqueUserName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        final User user = userRepository.findByName(s);
//        if (user == null)
//            return true;
//        else return false;
            return user == null;
    }
}

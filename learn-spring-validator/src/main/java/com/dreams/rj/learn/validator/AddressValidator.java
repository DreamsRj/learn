package com.dreams.rj.learn.validator;

import com.dreams.rj.learn.bean.Address;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AddressValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Address.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}

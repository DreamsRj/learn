package com.dreams.rj.learn.validator;

import com.dreams.rj.learn.bean.Address;
import com.dreams.rj.learn.bean.Customer;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CustomerValidator implements Validator {

    private AddressValidator addressValidator;

    public CustomerValidator(AddressValidator addressValidator) {
        if (addressValidator == null) {
            throw new IllegalArgumentException("the supplied [Validator] is required and must not be null !");
        }

        if (!addressValidator.supports(Address.class)) {
            throw new IllegalArgumentException("the supplied [Validator] must support validation of [Address] instance !");
        }

        this.addressValidator = addressValidator;
    }

    @Override

    public boolean supports(Class<?> clazz) {
        return Customer.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "field.required");

        Customer o = (Customer) target;
        try {
            errors.pushNestedPath("address");
            ValidationUtils.invokeValidator(this.addressValidator, o.getAddress(), errors);
        } finally {
            errors.popNestedPath();
        }
    }
}

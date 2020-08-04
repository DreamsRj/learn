package com.dreams.rj.learn.validator;

import com.dreams.rj.learn.bean.Person;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Person 类的验证器
 */
public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(Person.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        Person p = (Person) o;
        if (p.getAge() < 0) {
            errors.rejectValue("age", "negative value");
        } else if (p.getAge() > 110) {
            errors.rejectValue("age", "too.darn.old");
        }
    }
}

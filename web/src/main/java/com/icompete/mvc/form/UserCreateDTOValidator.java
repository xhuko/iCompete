package com.icompete.mvc.form;

import com.icompete.dto.UserCreateDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserCreateDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserCreateDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserCreateDTO userDTO = (UserCreateDTO) target;

        if (!(userDTO.getPassword().equals(userDTO.getPasswordConfirm()))) {
            errors.rejectValue("passwordConfirm", "", "Passwords do not match");
        }

    }
}

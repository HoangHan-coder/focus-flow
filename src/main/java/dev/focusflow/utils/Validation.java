package dev.focusflow.utils;


import dev.focusflow.dto.request.UserRegisterDTO;
import org.springframework.validation.BindingResult;

public class Validation {

    public static boolean passwordIsValidated(UserRegisterDTO userRegisterDTO) {
        return userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword());
    }
}

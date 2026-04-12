package dev.focusflow.utils;

public class Validation {

    public static boolean passwordIsValidated(String password,  String confirmPassword) {
        return password.equals(confirmPassword);
    }

}

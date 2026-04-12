package dev.focusflow.dto.request;

import jakarta.validation.constraints.*;

public class UserRegisterDTO {

    @NotBlank(message = "Password must not be blank")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\w\\s]).{8,}$",
            message = "Password must be at least 8 characters long and include uppercase, lowercase, a number, and a special character")
    private String password;

    @NotBlank(message = "Password must not be blank")
    private String confirmPassword;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Full name must not be blank")
    private String fullName;

    public UserRegisterDTO() {
    }

    public UserRegisterDTO(String password, String confirmPassword, String email, String fullName) {
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

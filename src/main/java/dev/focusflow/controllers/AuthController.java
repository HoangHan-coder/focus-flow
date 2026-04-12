package dev.focusflow.controllers;

import dev.focusflow.dto.request.UserRegisterDTO;
import dev.focusflow.entities.User;
import dev.focusflow.exceptions.DuplicateEmailException;
import dev.focusflow.services.UserService;
import dev.focusflow.utils.Validation;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginView(Model model,
                            @RequestParam(value = "error", required = false) String error) {

        if (error != null) {
            model.addAttribute("error", "Username or password incorrect!");
        }
        return "login";
    }

    @GetMapping("/registers")
    public String registerView(Model model) {
        model.addAttribute("user", new UserRegisterDTO());
        return "register";
    }

    @PostMapping("/registers")
    public String registerHandle(@ModelAttribute("user") @Valid UserRegisterDTO userRegisterDTO,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes
                                 ) {

        if (result.hasErrors()) {
            return "register";
        }

        if (!Validation.passwordIsValidated(userRegisterDTO.getPassword(), userRegisterDTO.getConfirmPassword())) {
            System.out.println("[ERROR] Passwords do not match!");
            result.rejectValue("confirmPassword", "error.confirmPassword", "Passwords do not match");
            return "register";
        }

        try {
            userService.register(userRegisterDTO);
        } catch (DuplicateEmailException e) {
            result.rejectValue("email", "error.email", e.getMessage());
            return "register";
        }

        redirectAttributes.addFlashAttribute("registrationSuccess", true);
        return "redirect:/auth/login";
    }
}

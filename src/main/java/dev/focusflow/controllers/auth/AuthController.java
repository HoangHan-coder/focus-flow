package dev.focusflow.controllers.auth;

import dev.focusflow.dto.request.UserRegisterDTO;
import dev.focusflow.exceptions.DuplicateEmailException;
import dev.focusflow.services.UserService;
import dev.focusflow.utils.Validation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
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

    private final Logger log = LoggerFactory.getLogger(AuthController.class);

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginView(Model model,
                            Authentication authentication,
                            @RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "auth", required = false) String auth) {

        log.info("authentication is {}", authentication);

        if (authentication != null && authentication.isAuthenticated()
                && !(authentication.getPrincipal() instanceof AnonymousAuthenticationToken)) {
            return "redirect:/dashboard";
        }

        if (auth != null) {
            model.addAttribute("error", "Your account is banned, please contact with admin!");
        }

        if (error != null) {
            model.addAttribute("error", "Username or password incorrect!");
        }

        return "auth/login";
    }

    @GetMapping("/registers")
    public String registerView(Model model) {
        model.addAttribute("user", new UserRegisterDTO());
        return "auth/register";
    }

    @PostMapping("/registers")
    public String registerHandle(@ModelAttribute("user") @Valid UserRegisterDTO userRegisterDTO,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes
                                 ) {

        if (result.hasErrors()) {
            return "auth/register";
        }

        if (!Validation.passwordIsValidated(userRegisterDTO.getPassword(), userRegisterDTO.getConfirmPassword())) {
            System.out.println("[ERROR] Passwords do not match!");
            result.rejectValue("confirmPassword", "error.confirmPassword", "Passwords do not match");
            return "auth/register";
        }

        try {
            userService.register(userRegisterDTO);
        } catch (DuplicateEmailException e) {
            result.rejectValue("email", "error.email", e.getMessage());
            return "auth/register";
        }

        redirectAttributes.addFlashAttribute("registrationSuccess", true);
        return "redirect:/auth/login";
    }
}

package dev.focusflow.config;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AccessDeniedException.class)
    public String AccessDeniedExceptionHandle(HttpServletRequest request) {
        String uri = request.getRequestURI();
        if (uri.startsWith("/auth/")) {
            log.debug("User access URI {} when authenticated.", uri);
            return "redirect:/tasks";
        }
        return "redirect:/auth/login";
    }


}

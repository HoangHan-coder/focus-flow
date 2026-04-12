package dev.focusflow.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder(12);
    }


    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        return http
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/assets/**", "/home", "/").permitAll()
                        .requestMatchers("/auth/**").anonymous()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/dashboard")
                        .failureHandler(((request, response, exception) -> {
                            String errorType = "error";
                            if (exception.getCause() instanceof DisabledException) {
                                errorType = "auth";
                            }
                            response.sendRedirect(request.getContextPath() + "/auth/login?" + errorType);
                        }))
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/auth/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .clearAuthentication(true)
                        .permitAll())
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler((req, res, e) -> {
                            String uri = req.getRequestURI();
                            if (uri.startsWith("/focus-flow/auth/")) {
                                res.sendRedirect(req.getContextPath() + "/dashboard");
                            } else {
                                res.sendRedirect(req.getContextPath() + "/auth/login");
                            }
                        }))
                .build();
    }

}

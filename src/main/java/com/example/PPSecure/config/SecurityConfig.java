package com.example.PPSecure.config;

import com.example.PPSecure.security.UserDetails;
import com.example.PPSecure.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    UserServiceImpl myUserDetailsServiceImpl;

    @Autowired
    public SecurityConfig(UserServiceImpl x) {
        this.myUserDetailsServiceImpl = x;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/login")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin(form -> form.successHandler((request, response, authentication) -> {
                            Object principal = authentication.getPrincipal();
                            String redirectUrl;
                            UserDetails userDetails = (UserDetails) principal;
                            boolean isAdmin = userDetails.getAuthorities().stream()
                                    .anyMatch(x -> x.getAuthority().equals("ROLE_ADMIN"));
                            if (isAdmin) {
                                redirectUrl = "/admin";
                            } else {
                                redirectUrl = "/authUser";
                        }
                    response.sendRedirect(redirectUrl);
                        })
                ).formLogin(form -> form.loginPage("/login"))
                .logout(config -> config.logoutSuccessUrl("/login")).build();
    }


    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

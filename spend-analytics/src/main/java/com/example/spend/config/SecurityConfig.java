package com.example.spend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${app.security.admin-user}")
    private String adminUser;
    @Value("${app.security.admin-pass}")
    private String adminPass;
    @Value("${app.security.analyst-user}")
    private String analystUser;
    @Value("${app.security.analyst-pass}")
    private String analystPass;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/js/**", "/images/**", "/h2-console/**").permitAll()
                .requestMatchers("/report/**").hasAnyRole("ADMIN", "ANALYST")
                .requestMatchers("/savings/**").hasAnyRole("ADMIN", "ANALYST")
                .requestMatchers("/cube/**").hasAnyRole("ADMIN", "ANALYST")
                .requestMatchers("/analysis/**").hasAnyRole("ADMIN", "ANALYST")
                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults())
            .logout(Customizer.withDefaults())
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))
            .csrf(csrf -> csrf.ignoringRequestMatchers(
                "/h2-console/**",
                "/cube/**",
                "/savings/**",
                "/analysis/maverick/**",
                "/report/**"
            ));
        return http.build();
    }

    @Bean
    public UserDetailsService users(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.withUsername(adminUser)
            .password(passwordEncoder.encode(adminPass))
            .roles("ADMIN")
            .build();
        UserDetails analyst = User.withUsername(analystUser)
            .password(passwordEncoder.encode(analystPass))
            .roles("ANALYST")
            .build();
        return new InMemoryUserDetailsManager(admin, analyst);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


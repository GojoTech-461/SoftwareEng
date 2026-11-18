package ma.um6p.confsub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security rules of the application (NFR-SEC-01 to NFR-SEC-04).
 * CSRF protection is enabled by default: we do not disable it.
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Public pages (NFR-SEC-02)
                .requestMatchers("/", "/login", "/register", "/css/**", "/js/**", "/images/**", "/error").permitAll()
                // Administration pages
                .requestMatchers("/admin/**").hasRole("ADMIN")
                // Everything else requires to be logged in
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")              // our own login page (login.html)
                .usernameParameter("email")       // the field is called "email", not "username"
                .defaultSuccessUrl("/", true)     // after login, go to the home page
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();   // NFR-SEC-01
    }
}

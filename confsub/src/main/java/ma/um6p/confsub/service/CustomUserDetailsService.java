package ma.um6p.confsub.service;

import ma.um6p.confsub.model.User;
import ma.um6p.confsub.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Tells Spring Security how to find a user in OUR database during login.
 * The "username" typed in the login form is the email.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailIgnoreCase(email.trim())
                .orElseThrow(() -> new UsernameNotFoundException("No user with email " + email));

        // Spring Security has its own "User" class: we write its full name to avoid confusion with ours
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPasswordHash())
                .roles(user.isAdmin() ? new String[]{"USER", "ADMIN"} : new String[]{"USER"})
                .disabled(!user.isEnabled())
                .build();
    }
}

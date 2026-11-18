package ma.um6p.confsub.service;

import ma.um6p.confsub.dto.RegistrationForm;
import ma.um6p.confsub.model.User;
import ma.um6p.confsub.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Business rules about user accounts.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection: Spring gives us the beans automatically
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates a new account (FR-ACC-01, FR-ACC-02, NFR-SEC-01).
     */
    @Transactional
    public User register(RegistrationForm form) {
        String email = form.getEmail().trim().toLowerCase();

        if (userRepository.existsByEmailIgnoreCase(email)) {
            throw new EmailAlreadyUsedException(email);
        }

        String hash = passwordEncoder.encode(form.getPassword());   // BCrypt

        User user = new User(
                email,
                hash,
                form.getFirstName().trim(),
                form.getLastName().trim(),
                form.getInstitution() == null ? null : form.getInstitution().trim()
        );
        return userRepository.save(user);
    }
}

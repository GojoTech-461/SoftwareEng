package ma.um6p.confsub.service;

/**
 * Thrown when someone tries to register with an email that already exists (FR-ACC-02).
 */
public class EmailAlreadyUsedException extends RuntimeException {

    public EmailAlreadyUsedException(String email) {
        super("Email already used: " + email);
    }
}

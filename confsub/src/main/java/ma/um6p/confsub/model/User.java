package ma.um6p.confsub.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

/**
 * A registered user of the platform (class "User" of the class diagram).
 * Roles per conference (Author, Reviewer, PC chair) will be stored later in ConferenceRole.
 */
@Entity
@Table(name = "users") // "user" is a reserved word in PostgreSQL
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;   // BCrypt hash, never the clear password (NFR-SEC-01)

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String institution;

    @Column(nullable = false)
    private boolean admin = false;   // global Administrator role

    @Column(nullable = false)
    private boolean enabled = true;  // false = account deactivated by the admin (FR-ACC-06)

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // JPA needs an empty constructor
    protected User() {
    }

    public User(String email, String passwordHash, String firstName, String lastName, String institution) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.institution = institution;
    }

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    // ----- Getters and setters -----

    public Long getId() { return id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getInstitution() { return institution; }
    public void setInstitution(String institution) { this.institution = institution; }

    public boolean isAdmin() { return admin; }
    public void setAdmin(boolean admin) { this.admin = admin; }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}

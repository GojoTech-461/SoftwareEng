package ma.um6p.confsub.repository;

import ma.um6p.confsub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Data access for users. Spring Data JPA writes the SQL from the method names.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailIgnoreCase(String email);   // SELECT ... WHERE lower(email) = lower(?)

    boolean existsByEmailIgnoreCase(String email);         // SELECT count(*) > 0 ...
}

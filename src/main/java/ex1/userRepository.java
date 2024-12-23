package ex1;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepository extends JpaRepository<user, Integer> {
    Optional<user> findByUserId(String userId);
}

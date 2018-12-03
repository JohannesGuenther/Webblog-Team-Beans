package de.awacademy.team_beans.user;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Max;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByNameAndPassword(String name, String password); //Optional - es kann sein, dass es ein User-objekt gibt, es kann aber auch sein,dass es keins gibt

    List<User> findAll();

    boolean existsByName(String name);
}

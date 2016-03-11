package com.aungthumoe.repositories;

import com.aungthumoe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Aung Thu Moe
 */
public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findOneByEmail(String email);
    Optional<User> findOneByUsername(String username);
}

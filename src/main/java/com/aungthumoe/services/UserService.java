package com.aungthumoe.services;

import com.aungthumoe.models.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collection;
import java.util.Optional;

/**
 * @author Aung Thu Moe
 */
public interface UserService {

    Optional<User> getUserByUsername(String username);

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(User user);

    UsernamePasswordAuthenticationToken authenticate(final String username, final String password);

    void update(User user);
}

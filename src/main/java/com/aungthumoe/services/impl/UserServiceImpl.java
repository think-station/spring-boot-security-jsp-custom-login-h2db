package com.aungthumoe.services.impl;

import com.aungthumoe.models.Role;
import com.aungthumoe.models.User;
import com.aungthumoe.repositories.UserRepository;
import com.aungthumoe.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * @author Aung Thu Moe
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll(new Sort("username"));
    }

    @Override
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public UsernamePasswordAuthenticationToken authenticate(final String username, final String password) {
        Optional<User> userOptional = this.getUserByUsername(username);
        if (userOptional.isPresent()) {
            LOGGER.debug("User found!");
        };
        if (userOptional.isPresent() && this.passwordEncoder.matches(password, userOptional.get().getPassword())) {
            for (Role role : userOptional.get().getRoles()) {
                role.getAuthorities().size();
            }
            return new UsernamePasswordAuthenticationToken(username, password, userOptional.get().getAuthorities());
        } else {
            throw new RuntimeException("User authentication failed");
        }
    }

    @Override
    public void update(User user) {
        this.userRepository.saveAndFlush(user);
    }


}

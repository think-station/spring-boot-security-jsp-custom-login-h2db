package com.aungthumoe.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Aung Thu Moe
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;


    @Column(name = "password", nullable = false)
    private String password;

    /**
     * roles this user belongs to
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Permission", joinColumns = {
        @JoinColumn(name = "userId", nullable = false, updatable = false)},
        inverseJoinColumns = {
            @JoinColumn(name = "authorityId",
                nullable = false, updatable = false)})
    private Set<Authority> authorities = new HashSet<>(0);

    public User() {
        super();
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof User) {
            return this.getId().equals(((User) object).getId());
        }
        return false;
    }

    @JsonIgnore
    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void addAuthorities(Authority authority) {
        this.authorities.add(authority);
    }

    public String toString() {
        return "User {" +
            "username: " + username + ", " +
            "email: " + email + ", " + "}";
    }

}

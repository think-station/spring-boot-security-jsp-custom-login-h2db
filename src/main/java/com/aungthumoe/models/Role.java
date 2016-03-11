package com.aungthumoe.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Aung Thu Moe
 */
@Entity
@Table(name = "Role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * name of the group
     */
    @Column
    private String name;
    /**
     * the authorities this group has
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Permission", joinColumns = {
        @JoinColumn(name = "roleId", nullable = false, updatable = false)},
        inverseJoinColumns = {
            @JoinColumn(name = "authorityId",
                nullable = false, updatable = false)})
    private Set<Authority> authorities = new HashSet<>();
    /**
     * the users in this group
     */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}

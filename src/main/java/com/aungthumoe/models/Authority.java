package com.aungthumoe.models;

/**
 * @author Aung Thu Moe
 */

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

/**
 * An authority represents a permission within the system
 */
@Entity
public class Authority implements Serializable, GrantedAuthority {

    public static final long serialVersionUID = 43L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    /**
     * the name of the permission
     */
    @Column
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getAuthority(){
        return type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

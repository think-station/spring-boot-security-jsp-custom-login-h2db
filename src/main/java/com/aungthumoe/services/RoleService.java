package com.aungthumoe.services;

import com.aungthumoe.models.Role;

/**
 * @author Aung Thu Moe
 */
public interface RoleService {
    Role create(Role role);
    Role findOne(Long id);
}

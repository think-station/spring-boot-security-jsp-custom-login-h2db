package com.aungthumoe.services.impl;

import com.aungthumoe.models.Role;
import com.aungthumoe.repositories.RoleRepository;
import com.aungthumoe.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Aung Thu Moe
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findOne(Long id) {
        return roleRepository.findOne(id);
    }
}

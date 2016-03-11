package com.aungthumoe.services.impl;

import com.aungthumoe.models.Authority;
import com.aungthumoe.repositories.AuthorityRepository;
import com.aungthumoe.services.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Aung Thu Moe
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority create(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public Authority findOne(Long id) {
        return authorityRepository.findOne(id);
    }
}

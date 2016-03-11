package com.aungthumoe.services;

import com.aungthumoe.models.Authority;

/**
 * @author Aung Thu Moe
 */
public interface AuthorityService {
    Authority create(Authority authority);
    Authority findOne(Long id);
}

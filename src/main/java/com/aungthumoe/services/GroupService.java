package com.aungthumoe.services;

import com.aungthumoe.models.Group;

/**
 * @author Aung Thu Moe
 */
public interface GroupService {
    Group create(Group group);
    Group findOne(Long id);
}

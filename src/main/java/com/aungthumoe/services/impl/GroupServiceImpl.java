package com.aungthumoe.services.impl;

import com.aungthumoe.models.Group;
import com.aungthumoe.repositories.GroupRepository;
import com.aungthumoe.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Aung Thu Moe
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Group create(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group findOne(Long id) {
        return groupRepository.findOne(id);
    }
}

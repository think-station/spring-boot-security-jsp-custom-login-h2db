package com.aungthumoe;

import com.aungthumoe.models.Authority;
import com.aungthumoe.models.Role;
import com.aungthumoe.models.User;
import com.aungthumoe.services.AuthorityService;
import com.aungthumoe.services.RoleService;
import com.aungthumoe.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author Aung Thu Moe
 */
@Component
public class AppListener implements ApplicationListener<ApplicationEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppListener.class);

    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private RoleService roleService;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if(applicationEvent instanceof ContextRefreshedEvent){
            LOGGER.debug("onApplicationEvent STARTING: " + applicationEvent.getClass().getSimpleName());

            Authority adminAuth = new Authority();
            adminAuth.setType("admin");
            this.authorityService.create(adminAuth);
            LOGGER.debug("onApplicationEvent() Authority created");

            Authority userAuth = new Authority();
            userAuth.setType("user");
            this.authorityService.create(userAuth);
            LOGGER.debug("onApplicationEvent() Authority created");

            Role adminRole = new Role();
            adminRole.setName("admins");
            this.roleService.create(adminRole);
            LOGGER.debug("onApplicationEvent() Role created");
            adminRole.getAuthorities().add(adminAuth);
            adminRole.getAuthorities().add(userAuth);

            Role userRole = new Role();
            userRole.setName("users");
            this.roleService.create(userRole);
            LOGGER.debug("onApplicationEvent() Role created");
            userRole.getAuthorities().add(userAuth);

            User admin = new User("admin", "admin@localhost", "admin");
            this.userService.create(admin);
            LOGGER.debug("onApplicationEvent() User created");
            admin.getRoles().add(adminRole);
            this.userService.update(admin);
            LOGGER.debug("onApplicationEvent() User updated");

            User user = new User("user", "user@localhost", "user");
            this.userService.create(user);
            LOGGER.debug("onApplicationEvent() User created");
            user.getRoles().add(userRole);
            this.userService.update(user);
            LOGGER.debug("onApplicationEvent() User updated");
        }
    }
}

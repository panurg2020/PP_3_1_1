package com.katastudy.app.util;

import com.katastudy.app.model.Role;
import com.katastudy.app.model.User;
import com.katastudy.app.service.RoleServiceImpl;
import com.katastudy.app.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserInit {

    private final UserServiceImpl userService;

    private final RoleServiceImpl roleService;

    @Autowired
    public UserInit(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void initialize() {
        Role roleAdmin = new Role(1L,"ROLE_ADMIN");
        Role roleUser = new Role(2L,"ROLE_USER");
        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);
        User admin = new User();
        admin.setName("Ivan");
        admin.setSurname("Ivanov");
        admin.setEmail("admin@mail.ru");
        admin.setUsername("admin");
        admin.setPassword("Admin123");
        admin.addRole(roleAdmin);
        userService.saveUser(admin);
    }
}

package com.katastudy.app.util;

import com.katastudy.app.service.RoleService;
import com.katastudy.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInit {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

  //  @PostConstruct
  //  public void createUsersWithRoles() {

  //     Set<Role> set = new HashSet<>();

   //     User user1 = new User(new BCryptPasswordEncoder(8).encode("Admin123."), 3L, "Semen", "Semenovich", 20,  "semen@mail.ru", set );

    //    userService.saveUser (user1);

   // }
}

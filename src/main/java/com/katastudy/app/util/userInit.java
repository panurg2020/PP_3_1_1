package com.katastudy.app.util;

import com.katastudy.app.model.Role;
import com.katastudy.app.model.User;
import com.katastudy.app.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class userInit {

    private final UserService userService;


    public userInit(UserService userService) {
        this.userService = userService;
    }

      @PostConstruct
      public void createUsersWithRoles() {
          Role role1 = new Role("ADMIN");
          Role role2 = new Role( "USER");

        List<Role> set = new ArrayList<>();
          set.add(role1);
        //  set.add(role2);

          User user1 = new User( 1l, "sema", (new BCryptPasswordEncoder(8).encode("Admin123.")),"Semen", "Semenovich",  "semen@mail.ru", set);

          userService.addUser(user1);

     }
}
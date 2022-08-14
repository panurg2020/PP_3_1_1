package com.katastudy.app.controller;

import com.katastudy.app.model.User;
import com.katastudy.app.service.RoleServiceImpl;
import com.katastudy.app.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping()
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;


    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String users(Principal principal, Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "admin/user-list";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "admin/edit")
    public String editUserForm (@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roleAdmin", roleService.getAdminRole());
        model.addAttribute("roleUser", roleService.getUserRole());
        return "admin/userInAdmin";
    }

    @GetMapping("/admin/create")
    public String createUserForm (Principal principal, Model model) {
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        model.addAttribute("roleAdmin", roleService.getAdminRole());
        model.addAttribute("roleUser", roleService.getUserRole());
        return "admin/create";
    }

    @PostMapping("/{id}/edit")
    public String editUser(@ModelAttribute("user") User user) {
        //Set<Role> roles = new HashSet<>();
        //roles.add(roleService.getRole("USER"));
        //if (roleAdmin != null && roleAdmin.equals("ADMIN")) {
       //     roles.add(roleService.getRole("ADMIN"));
      //  }
      // user.setRoles(roles);
       userService.editUser(user);
       return "redirect:/admin";
    }

    @PostMapping("/add")
    public String createUser (@ModelAttribute("user") User user) {
       // Set<Role> roles = new HashSet<>();
      //  roles.add(roleService.getRole("USER"));
       // if (roleAdmin != null && roleAdmin.equals("ADMIN")) {
      //      roles.add(roleService.getRole("ADMIN"));
      //  }
      //  user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String showUserInfo (Principal principal, Model model) {
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "admin/userInAdmin";
    }
}



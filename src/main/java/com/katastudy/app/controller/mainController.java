package com.katastudy.app.controller;

import com.katastudy.app.repository.RoleRepository;
import com.katastudy.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.katastudy.app.model.Role;
import com.katastudy.app.model.User;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class mainController {
    private UserService userService;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

  //  @GetMapping("/")
   // public String welcomeUsers() {
   //     return "/admin/user-list";
   // }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("list", userService.listUsers());
        return "admin/user-list";
    }

    @GetMapping("/user/{id}")
    public String userPage(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUser(id));
        return "user";
    }

    @GetMapping("/user")
    public String userPageForName(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/admin")
    public String adminPageForName(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "admin/userInAdmin";
    }

    @GetMapping("/add")
    public String newUser(Model model) {
        if (userService.listRoles().isEmpty()) {
            roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("USER"));
        }
        model.addAttribute("user", new User());
        model.addAttribute("roles", userService.listRoles());
        return "admin/create";
    }

    @PostMapping("/add")
    public String userCreate(@ModelAttribute("user") User user, @RequestParam(value = "role") String role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userService.findRolesByName(role));
        userService.addUser(user);
        return "redirect:/admin/user-list";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("roles", userService.listRoles());
        model.addAttribute("user", userService.getUser(id));
        return "admin/edit";
    }

    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") long id, @RequestParam(value = "role") String role) {
        user.setRoles(userService.findRolesByName(role));
        userService.updateUser(user);
        return "redirect:/admin/user-list";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/user-list";
    }
}


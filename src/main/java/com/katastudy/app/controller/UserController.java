package com.katastudy.app.controller;

import com.katastudy.app.model.User;
import com.katastudy.app.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public void setUserService (UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String findAll(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("users", user);
        return "user";
    }
}

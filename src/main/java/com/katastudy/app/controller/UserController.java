package com.katastudy.app.controller;

import com.katastudy.app.model.User;
import com.katastudy.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    ModelAndView modelAndView = new ModelAndView();

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView allUsers() {
        List<User> users = userService.allUsers();
        modelAndView.setViewName("user-list");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @RequestMapping(value = "/user-create", method = RequestMethod.GET)
    public ModelAndView addUserForm(User user) {
        modelAndView.setViewName("user-create");
        return modelAndView;
    }

    @RequestMapping(value = "/user-create", method = RequestMethod.POST)
    public ModelAndView createUser(@ModelAttribute("user") User user) {
        modelAndView.setViewName("redirect:/users");
        userService.add(user);
        return modelAndView;
    }

    @RequestMapping(value = "user-delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") long id) {
        User user = userService.getById(id);
        modelAndView.setViewName("redirect:/users");
        userService.delete(id);
        return modelAndView;
    }

    @RequestMapping(value = "/user-update/{id}", method = RequestMethod.GET)
    public ModelAndView updateUserForm(@PathVariable("id") long id) {
        User user = userService.getById(id);
        modelAndView.setViewName("user-update");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/user-update", method = RequestMethod.POST)
    public ModelAndView updateUser(@ModelAttribute("user") User user) {
        modelAndView.setViewName("redirect:/users");
        userService.add(user);
        return modelAndView;
    }
}
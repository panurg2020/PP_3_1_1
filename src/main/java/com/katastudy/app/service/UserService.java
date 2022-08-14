package com.katastudy.app.service;

import com.katastudy.app.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService  {

    List <User> getAllUsers();
    void saveUser(User user);
    User getUserById(Long id);
    void deleteUserById(Long id);
    UserDetails loadUserByUsername(String email);
    void editUser(User user);
}
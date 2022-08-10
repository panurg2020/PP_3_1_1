package com.katastudy.app.service;

import com.katastudy.app.model.User;

import java.util.List;

public interface UserService {

    List<User> allUsers();

    void add (User user);

    void delete (long id);

    User findById(long id);
}

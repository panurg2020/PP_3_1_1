package com.katastudy.app.service;

import com.katastudy.app.model.User;

import java.util.List;

public interface UserService {

    User getById (long id);

    List<User> allUsers();

    void add (User user);

    void delete (long id);

    void userUpdate (long id, User userUpdate);
}

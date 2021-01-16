package com.oktenwebjava.service;

import com.oktenwebjava.entity.User;

import java.util.List;

public interface IUserServise {
    User saveUser(User user);

    List<User> getallUsers();

    User getUserById(int id);

    void deleteUser(int id);

    User updateUser(int id, User user) throws IllegalAccessException;
}

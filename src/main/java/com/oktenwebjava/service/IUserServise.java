package com.oktenwebjava.service;

import com.oktenwebjava.dto.UserCreateDto;
import com.oktenwebjava.dto.UserDto;
import com.oktenwebjava.entity.User;

import java.util.List;

public interface IUserServise {
    UserDto saveUser(UserCreateDto user);

    List<UserDto> getallUsers();

    UserDto getUserById(int id);

    void deleteUser(int id);

    User updateUser(int id, User user) throws IllegalAccessException;
}

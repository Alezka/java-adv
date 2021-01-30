package com.oktenwebjava.service;

import com.oktenwebjava.dto.UserCreateDto;
import com.oktenwebjava.dto.UserDto;
import com.oktenwebjava.dto.UserPageDto;
import com.oktenwebjava.dto.UserProfessionDto;
import com.oktenwebjava.entity.User;
import org.springframework.data.domain.PageRequest;

public interface IUserServise {
    UserDto saveUser(UserCreateDto user);

    UserPageDto getallUsers(PageRequest pageRequest);

    UserDto getUserById(int id);

    void deleteUser(int id);

    User updateUser(int id, User user) throws IllegalAccessException;

    UserProfessionDto getUserProfessionDto(String title);
}

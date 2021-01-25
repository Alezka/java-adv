package com.oktenwebjava.service;

import com.oktenwebjava.dao.UserRepository;
import com.oktenwebjava.dto.BadRequestExeption;
import com.oktenwebjava.entity.User;
import org.apache.commons.lang3.CharUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService implements IUserServise {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public User saveUser(User user) {
        final char firstLetterOfName = user.getName().charAt(0);
           if(! CharUtils.isAsciiAlphaUpper(firstLetterOfName)){
//           throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Name should start with capital leter");
               throw new BadRequestExeption("Name should start with capital letter");
       };
        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getallUsers() {
        return userRepository.findAll();
    }

    @Override
    @Async
    public User getUserById(int id) {
        return userRepository.getOne(id);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(int id, User user) throws IllegalAccessException {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.saveAndFlush(user);
        } else {
            throw new IllegalAccessException("No user is such id" + id);
        }
    }
}

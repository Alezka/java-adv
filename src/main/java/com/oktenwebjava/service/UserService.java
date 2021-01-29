package com.oktenwebjava.service;

import com.oktenwebjava.dao.ProfessionRepository;
import com.oktenwebjava.dao.UserRepository;
import com.oktenwebjava.dto.BadRequestExeption;
import com.oktenwebjava.dto.UserCreateDto;
import com.oktenwebjava.entity.Profession;
import com.oktenwebjava.entity.User;
import org.apache.commons.lang3.CharUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserServise {
    private UserRepository userRepository;
    private ProfessionRepository professionRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(UserCreateDto user) {
        final char firstLetterOfName = user.getName().charAt(0);
        if (!CharUtils.isAsciiAlphaUpper(firstLetterOfName)) {
//           throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Name should start with capital leter");
            throw new BadRequestExeption("Name should start with capital letter");
        }
        User userEntity = new User();
        userEntity.setName(user.getName());
        userEntity.setAge(user.getAge());
        final Optional<Profession> profession = professionRepository.findById(user.getProfessionId());
        final Profession existingProfession =
                profession.orElseThrow(() -> new BadRequestExeption("No profession with such id"));
        userEntity.setProfession(existingProfession);
        return userRepository.saveAndFlush(userEntity);
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

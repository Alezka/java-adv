package com.oktenwebjava.service;

import com.oktenwebjava.dao.ProfessionRepository;
import com.oktenwebjava.dao.UserRepository;
import com.oktenwebjava.dto.*;
import com.oktenwebjava.entity.Profession;
import com.oktenwebjava.entity.User;
import org.apache.commons.lang3.CharUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserServise {
    private UserRepository userRepository;
@Autowired
    public UserService(UserRepository userRepository, ProfessionRepository professionRepository) {
        this.userRepository = userRepository;
        this.professionRepository = professionRepository;
    }

    private ProfessionRepository professionRepository;



//    @Autowired
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public UserDto saveUser(UserCreateDto user) {
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
        return convertToUserDto(userRepository.saveAndFlush(userEntity));
    }

    @Override
    public UserPageDto getallUsers(PageRequest pageRequest) {
        final Page<User> userPage = userRepository.findAll(pageRequest);
        final List<UserDto> users = userPage.stream().map(user ->
                new UserDto(user.getId(), user.getName(), user.getAge(), user.getProfession().getTitle())
        ).collect(Collectors.toList());
        return new UserPageDto(users,userPage.getTotalPages());
    }

    @Override
    @Async
    public UserDto getUserById(int id) {
        return convertToUserDto(userRepository.getOne(id));
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

    @Override
    public UserProfessionDto getUSersByProfessionTitle(String title) {
        final Profession profession = professionRepository.findUsersByProfessionTitle(title);
        final String professionTitle = profession.getTitle();
        final  int  professionId= profession.getId();
        final List  <User> users = profession.getUsers();
        final List<UserDto> userDtos = users.stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getAge(), professionTitle))
                .collect(Collectors.toList());
        return new UserProfessionDto(professionId,userDtos);
    }

    private UserDto convertToUserDto(User user){
        return new UserDto(user.getId(),user.getName(),user.getAge(),user.getProfession().getTitle());
    }
}

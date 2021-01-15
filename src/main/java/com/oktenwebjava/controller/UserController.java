package com.oktenwebjava.controller;

import com.oktenwebjava.dao.UserRepository;
import com.oktenwebjava.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createuser(@RequestBody User user) {
        return userRepository.saveAndFlush(user);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") int id) {
        return userRepository.getOne(id);
    }

    @PutMapping("/{id}")
    public User updateById(@RequestBody User newUser, @PathVariable("id") int id) throws IllegalAccessException {
        if (userRepository.existsById(id)) {
            newUser.setId(id);
            return userRepository.saveAndFlush(newUser);
        } else {
            throw new IllegalAccessException("No user is such id" + id);
        }
    }
//    @DeleteMapping("/{id}")
//    public List<User> deleteById(@PathVariable("id") int id) {
//        Iterator<User> iterator = users.iterator();
//        while (iterator.hasNext()) {
//            User next = iterator.next();
//            if (next.getId() == id) {
//                iterator.remove();
//            }
//        }
//        return users;
//    }
    @DeleteMapping("/{id}")
    public void delepeById(@PathVariable("id") int id) throws IllegalAccessException {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new IllegalAccessException("No user is such id" + id);
        }
        userRepository.deleteById(id);
    }
}

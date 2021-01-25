package com.oktenwebjava.controller;

import com.oktenwebjava.entity.User;
import com.oktenwebjava.service.IUserServise;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserServise userServise;//автовайредься завжди інтерфейси а не класи

//private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public List<User> getAllUsers() {
        return userServise.getallUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Valid User user) {
        log.info("Handled POST request with body: {}", user);
        return userServise.saveUser(user);
    }

    @GetMapping("/user")
    public User getById(@PathVariable int id) {
        return userServise.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateById(@RequestBody User newUser, @PathVariable("id") int id) throws IllegalAccessException {
        return userServise.updateUser(id, newUser);
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
//    @DeleteMapping("/{id}")
//    public void deleteById(@PathVariable("id") int id) throws IllegalAccessException {
//        Optional<User> userOptional = userRepository.findById(id);
//        if (!userOptional.isPresent()) {
//            throw new IllegalAccessException("No user is such id" + id);
//        }
//        userRepository.deleteById(id);
//    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") int id) {
        userServise.deleteUser(id);
    }

//    @InitBinder
//    public void initBinder(WebDataBinder webDataBinder){
//        webDataBinder.addValidators(new UserValidator());
//    }
}

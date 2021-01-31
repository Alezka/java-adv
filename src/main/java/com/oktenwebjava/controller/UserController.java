package com.oktenwebjava.controller;

import com.oktenwebjava.dto.UserCreateDto;
import com.oktenwebjava.dto.UserDto;
import com.oktenwebjava.dto.UserPageDto;
import com.oktenwebjava.dto.UserProfessionDto;
import com.oktenwebjava.entity.User;
import com.oktenwebjava.service.IUserServise;
import com.oktenwebjava.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    public UserPageDto getAllUsers(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "3") int size ) {
        return userServise.getallUsers(PageRequest.of(page,size));
    }

    @GetMapping("/profession/{title}")
    public UserProfessionDto getUSersByProfessionTitle(@PathVariable String title){
        return userServise.getUSersByProfessionTitle(title);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody @Valid UserCreateDto user) {
        log.info("Handled POST request with body: {}", user);
        return userServise.saveUser(user);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable int id) {
       return userServise.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateById( @RequestBody User newUser, @PathVariable("id") int id) throws IllegalAccessException {
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

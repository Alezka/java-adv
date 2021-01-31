package com.oktenwebjava.service;

import com.oktenwebjava.dao.ProfessionRepository;
import com.oktenwebjava.dao.UserRepository;
import com.oktenwebjava.dto.UserDto;
import com.oktenwebjava.dto.UserProfessionDto;
import com.oktenwebjava.entity.Profession;
import com.oktenwebjava.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProfessionRepository professionRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void givenProfessionTittleWhenGettingUsersByProfessionTitleThenReturtUserProfessionDto() {
        User user1 = new User();
        user1.setId(1);
        user1.setName("Name 1");
        User user2 = new User();
        user2.setId(2);
        user2.setName("Name 2");

        final String professonTitle = "Programing";
        Profession profession = new Profession();
        profession.setId(1);
        profession.setTitle(professonTitle);
        profession.setUsers(Arrays.asList(user1, user2));

        Mockito.when(professionRepository.findUsersByProfessionTitle(ArgumentMatchers.anyString()))
                .thenReturn(profession);

        UserDto userDto1 = new UserDto(user1.getId(),user1.getName(),20,professonTitle);
        UserDto userDto2 = new UserDto(user2.getId(),user2.getName(),20,professonTitle);

        UserProfessionDto expected = new  UserProfessionDto(1,Arrays.asList(userDto1,userDto2));

        final UserProfessionDto actual = userService.getUSersByProfessionTitle(professonTitle);

        Assertions.assertThat(actual.getUsers()).size().isEqualTo(2);
        Assertions.assertThat(actual.getProfessionId()).isEqualTo(expected.getProfessionId());

    }
}

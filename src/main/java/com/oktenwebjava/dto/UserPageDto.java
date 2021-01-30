package com.oktenwebjava.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@AllArgsConstructor
@Getter
public class UserPageDto {
    private List<UserDto> users;
    private int totalpages;

}

package com.oktenwebjava.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class UserDto {
    private int userId;
    private String Name;
    private int age;
    private String titleOfProfession;
}

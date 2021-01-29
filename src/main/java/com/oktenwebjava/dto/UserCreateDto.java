package com.oktenwebjava.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserCreateDto {
    private String name;
    private int age;
    private int professionId;

}

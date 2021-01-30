package com.oktenwebjava.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class UserProfessionDto {
    @JsonProperty("profession_id")
    private int professionId;

    private List<UserDto> users;

}

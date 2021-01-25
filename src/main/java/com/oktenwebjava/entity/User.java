package com.oktenwebjava.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.oktenwebjava.Validation.UniqueUserName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //    @Column(name = "user_name")
//    @UniqueUserName
    @NotBlank
    private String name;
    @Positive
    @Column(length = 3)
    private int age;
    private boolean status;

}


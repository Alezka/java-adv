package com.oktenwebjava.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@JsonIgnoreProperties({"hybernateLazyInitializer","handler"})
public class Profession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private LocalDate dateOfGraduation;
//

    @OneToMany(mappedBy = "profession",fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

//    @OneToMany(mappedBy = "professionRewardet")
//    private Set<User> awardUsers = new HashSet<>();
}


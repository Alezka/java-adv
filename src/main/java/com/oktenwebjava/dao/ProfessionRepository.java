package com.oktenwebjava.dao;

import com.oktenwebjava.entity.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.Select;

public interface ProfessionRepository extends JpaRepository<Profession,Integer> {

    @Query("Select p from Profession p join  p.users where p.title like :title")
    Profession findUsersByProfessionTitle(String title);

}


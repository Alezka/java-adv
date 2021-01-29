package com.oktenwebjava.dao;

import com.oktenwebjava.entity.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionRepository extends JpaRepository<Profession,Integer> {
}

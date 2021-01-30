package com.oktenwebjava.dao;


import com.oktenwebjava.entity.Profession;
import com.oktenwebjava.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer>  {

        @Query("Select u from User u  where u.name like :name")
    User findByName(String name);

}

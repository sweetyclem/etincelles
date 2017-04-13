package com.etincelles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.etincelles.entities.User;
import com.etincelles.enumeration.Category;
import com.etincelles.enumeration.City;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail( String email );

    List<User> findByCategory( Category category );

    List<User> findByCity( City city );

    List<User> findByfirstNameContaining( String firstName );

    List<User> findBylastNameContaining( String lastName );

    @Query( "select u from User u order by u.lastName" )
    List<User> findAll();
}

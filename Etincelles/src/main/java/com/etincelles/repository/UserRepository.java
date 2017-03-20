package com.etincelles.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.etincelles.entities.User;
import com.etincelles.enumeration.Category;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail( String email );

    List<User> findByCategory( Category category );
}

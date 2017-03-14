package com.etincelles.repository;

import org.springframework.data.repository.CrudRepository;

import com.etincelles.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail( String email );
}

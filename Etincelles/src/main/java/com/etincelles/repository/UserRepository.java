package com.etincelles.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.etincelles.entities.User;
import com.etincelles.enumeration.Category;
import com.etincelles.enumeration.City;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByEmail( String email );

    List<User> findByCategory( Category category );

    List<User> findByCity( City city );

    List<User> findByfirstNameContaining( String firstName );

    List<User> findBylastNameContaining( String lastName );

    @Query( "select u from User u order by u.lastName" )
    Page<User> findAll( Pageable pageable );

    @Query( "SELECT u FROM User u WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%',:keyword, '%')) OR " +
            "LOWER(u.description) LIKE LOWER(CONCAT('%',:keyword, '%')) OR LOWER(u.lastName) LIKE LOWER(CONCAT('%',:keyword, '%'))"
            + "OR LOWER(u.currentPosition) LIKE LOWER(CONCAT('%',:keyword, '%'))" )
    List<User> findByKeyword( String keyword );
}

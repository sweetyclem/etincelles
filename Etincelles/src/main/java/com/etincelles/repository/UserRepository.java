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

    @Query( "select u from User u where u.enabled = true and u.firstName is not null and u.firstName != '' order by u.lastName" )
    Page<User> findAll( Pageable pageable );

    @Query( "SELECT u FROM User u WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%',?1, '%')) OR " +
            "LOWER(u.description) LIKE LOWER(CONCAT('%',?1, '%')) OR LOWER(u.lastName) LIKE LOWER(CONCAT('%',?1, '%'))"
            + "OR LOWER(u.currentPosition) LIKE LOWER(CONCAT('%',?1, '%'))"
            + "OR LOWER(u.sector) LIKE LOWER(CONCAT('%',?1, '%'))"
            + "OR LOWER(u.city) LIKE LOWER(CONCAT('%',?1, '%'))" + "OR LOWER(u.type) LIKE LOWER(CONCAT('%',?1, '%'))" )
    List<User> findFromKeyword( String keyword );
}

package com.etincelles.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.etincelles.entities.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByEmail( String email );

    @Query( "SELECT u FROM User u WHERE u.enabled = true AND u.firstName is not null AND u.firstName != '' ORDER BY u.lastName" )
    Page<User> findAll( Pageable pageable );

    @Query( "SELECT u FROM User u WHERE u.enabled = true AND u.firstName is not null AND u.firstName != ''"
            + " AND LOWER(u.firstName) LIKE LOWER(CONCAT('%',?1, '%'))"
            + " OR LOWER(u.description) LIKE LOWER(CONCAT('%',?1, '%'))"
            + " OR LOWER(u.lastName) LIKE LOWER(CONCAT('%',?1, '%'))"
            + " OR LOWER(u.currentPosition) LIKE LOWER(CONCAT('%',?1, '%'))"
            + " OR LOWER(u.sector) LIKE LOWER(CONCAT('%',?1, '%'))"
            + " OR LOWER(u.city) LIKE LOWER(CONCAT('%',?1, '%'))"
            + " OR LOWER(u.type) LIKE LOWER(CONCAT('%',?1, '%'))"
            + " ORDER BY u.lastName" )
    Page<User> findByKeywordAndSort( String keyword, Pageable pageable );
}

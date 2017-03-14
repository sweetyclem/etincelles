package com.etincelles.repository;

import org.springframework.data.repository.CrudRepository;

import com.etincelles.entities.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByname( String name );
}

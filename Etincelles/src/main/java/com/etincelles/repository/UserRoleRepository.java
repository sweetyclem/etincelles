
package com.etincelles.repository;

import org.springframework.data.repository.CrudRepository;

import com.etincelles.entities.security.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

}
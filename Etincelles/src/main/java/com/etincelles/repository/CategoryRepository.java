package com.etincelles.repository;

import org.springframework.data.repository.CrudRepository;

import com.etincelles.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByname( String name );
}

package com.etincelles.service;

import java.util.List;

import com.etincelles.entities.Category;

public interface CategoryService {
    public Category save( Category category );

    public Category createCategory( Category category );

    List<Category> findAll();
}

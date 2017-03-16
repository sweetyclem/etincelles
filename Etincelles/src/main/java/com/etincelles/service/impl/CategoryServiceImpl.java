package com.etincelles.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etincelles.entities.Category;
import com.etincelles.repository.CategoryRepository;
import com.etincelles.service.CategoryService;
import com.etincelles.service.UserService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOG = LoggerFactory.getLogger( UserService.class );

    @Autowired
    private CategoryRepository  categoryRepository;

    @Override
    public Category createCategory( Category category ) {
        Category localCategory = categoryRepository.findByname( category.getName() );

        if ( localCategory != null ) {
            LOG.info( "category {} already exists. Nothing will be done.", category.getName() );
        } else {
            localCategory = categoryRepository.save( category );
        }

        return localCategory;
    }

    @Override
    public Category save( Category category ) {
        return categoryRepository.save( category );
    }

    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
    }
}

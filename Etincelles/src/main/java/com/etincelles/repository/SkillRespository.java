package com.etincelles.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.etincelles.entities.Skill;

public interface SkillRespository extends CrudRepository<Skill, Long> {
    Skill findByname( String name );

    List<Skill> findAll();
}

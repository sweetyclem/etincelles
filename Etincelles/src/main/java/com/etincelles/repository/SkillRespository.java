package com.etincelles.repository;

import org.springframework.data.repository.CrudRepository;

import com.etincelles.entities.Skill;

public interface SkillRespository extends CrudRepository<Skill, Long> {
    Skill findByname( String name );
}

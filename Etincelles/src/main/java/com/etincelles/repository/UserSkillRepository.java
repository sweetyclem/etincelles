package com.etincelles.repository;

import org.springframework.data.repository.CrudRepository;

import com.etincelles.entities.Skill;
import com.etincelles.entities.User;
import com.etincelles.entities.UserSkill;

public interface UserSkillRepository extends CrudRepository<UserSkill, Long> {
    UserSkill findByUserAndSkill( User user, Skill skill );

}

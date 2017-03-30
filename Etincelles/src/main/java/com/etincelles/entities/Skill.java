package com.etincelles.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Skill {
    @Id
    private int            skillId;
    private String         name;

    @OneToMany( mappedBy = "skill", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private Set<UserSkill> userSkills = new HashSet<>();

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId( int skillId ) {
        this.skillId = skillId;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public Set<UserSkill> getUserSkills() {
        return userSkills;
    }

    public void setUserSkills( Set<UserSkill> userSkills ) {
        this.userSkills = userSkills;
    }
}

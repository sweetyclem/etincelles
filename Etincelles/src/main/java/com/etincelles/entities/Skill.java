package com.etincelles.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Skill {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id", nullable = false, updatable = false )
    private int    skillId;
    private String name;

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
}

package com.etincelles.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "user_category" )
public class UserCategory {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long     userCategoryId;

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "user_id" )
    private User     user;

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "category_id" )
    private Category category;
}

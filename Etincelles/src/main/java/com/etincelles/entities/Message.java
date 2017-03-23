package com.etincelles.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Message {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id", nullable = false, updatable = false )
    private Long          id;
    private String        title;
    @Column( columnDefinition = "text" )
    private String        text;
    @Transient
    private MultipartFile picture;
    private boolean       hasPicture = false;

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture( MultipartFile picture ) {
        this.picture = picture;
    }

    public boolean isHasPicture() {
        return hasPicture;
    }

    public void setHasPicture( boolean hasPicture ) {
        this.hasPicture = hasPicture;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText( String text ) {
        this.text = text;
    }

}
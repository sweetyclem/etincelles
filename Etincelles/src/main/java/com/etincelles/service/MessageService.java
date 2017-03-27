package com.etincelles.service;

import java.util.List;

import com.etincelles.entities.Message;

public interface MessageService {
    Message findById( Long id );

    Message createMessage( Message message );

    List<Message> searchAll( String text );

    List<Message> findAll();
}

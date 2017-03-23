package com.etincelles.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etincelles.entities.Message;
import com.etincelles.repository.MessageRepository;
import com.etincelles.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public Message findById( Long id ) {
        return messageRepository.findById( id );
    }

    @Override
    public List<Message> searchAll( String text ) {
        List<Message> TitleList = messageRepository.findByTitleContaining( text );
        List<Message> TextList = messageRepository.findByTextContaining( text );
        List<Message> messageList = new ArrayList<>();

        for ( Message message : TextList ) {
            if ( !TitleList.contains( message ) ) {
                messageList.add( message );
            }
        }

        return messageList;
    }

    @Override
    public List<Message> findAll() {
        List<Message> messageList = (List<Message>) messageRepository.findAll();
        return messageList;
    }

    @Override
    public Message createMessage( Message message ) {
        Message localMessage = messageRepository.save( message );

        return localMessage;
    }

}

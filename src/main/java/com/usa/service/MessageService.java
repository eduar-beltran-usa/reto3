package com.usa.service;

import com.usa.entities.Message;
import com.usa.repository.MessageRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessage(int id) {
        return messageRepository.findById(id);
    }

    public Message save(Message message) {
        if (message.getIdMessage() == null) {
            return messageRepository.save(message);
        } else {
            Optional<Message> mess = messageRepository.findById(message.getIdMessage());
            if (mess.isEmpty()) {
                return messageRepository.save(message);
            } else {
                return message;
            }
        }
    }

    public Message update(Message message) {
        if (message.getIdMessage() != null) {
            Optional<Message> mess = messageRepository.findById(message.getIdMessage());
            if (!mess.isEmpty()) {
                if (message.getMessageText() != null) {
                    mess.get().setMessageText(message.getMessageText());
                }
                if (message.getClient() != null) {
                    mess.get().setClient(message.getClient());
                }
                if (message.getMotorbike() != null) {
                    mess.get().setMotorbike(message.getMotorbike());
                }
                messageRepository.save(mess.get());
                return mess.get();
            } else {
                return message;
            }
        } else {
            return message;
        }

    }

    public boolean deleteMessage(int id) {
        Boolean messagBoolean = messageRepository.findById(id).map(message -> {
            messageRepository.delete(message);
            return true;
        }).orElse(false);
        return messagBoolean;
    }
}

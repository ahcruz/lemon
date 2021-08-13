package com.lemon.message.service.implementation;

import com.lemon.message.domain.Message;
import com.lemon.message.external.IFoaasService;
import com.lemon.message.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageService implements IMessageService {

    @Autowired
    public IFoaasService foaasService;

    @Override
    public Message getMessage() {
        return new Message(foaasService.getMessage(), new Date());
    }
}

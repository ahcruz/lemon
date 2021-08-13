package com.lemon.message.controller;

import com.lemon.message.domain.Message;
import com.lemon.message.service.IMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/lemon/demo")
@Slf4j
public class MessageController {

    @Autowired
    private IMessageService messageService;

    @GetMapping(path = "/message")
    public ResponseEntity<Message> GetMessage(){
            return ResponseEntity.ok(messageService.getMessage());
    }
}

package com.handson.basic.controller;

import com.handson.basic.model.MessageAndPhones;
import com.handson.basic.util.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SmsController {

    @Autowired
    SmsService smsService;

    @RequestMapping(value = "/sms", method = RequestMethod.POST)
    public ResponseEntity<?> smsAll(@RequestBody MessageAndPhones messageAndPhones) {
        new Thread(() -> {
            messageAndPhones.getPhones()
                    .parallelStream()
                    .forEach(phone -> {
                        // Create a new MessageAndPhones object for each phone number
                        MessageAndPhones singlePhoneMessage = new MessageAndPhones();
                        singlePhoneMessage.setMessage(messageAndPhones.getMessage());
                        singlePhoneMessage.getPhones();

                        smsService.send(singlePhoneMessage);  // Send the full object
                    });
        }).start();
        return new ResponseEntity<>("SENDING", HttpStatus.OK);
    }
}


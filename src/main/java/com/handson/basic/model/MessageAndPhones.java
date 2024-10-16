package com.handson.basic.model;

import java.util.List;

public class MessageAndPhones {
    String message;
    List<String> phones;

    public String getMessage() {
        return message;
    }

    public List<String> getPhones() {
        return phones;
    }

    public MessageAndPhones() {
        this.message = message;
        this.phones = phones;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
package com.demo.websocket.domain;

import org.springframework.beans.factory.annotation.Autowired;

public class TestMessage {
    private String message;

    @Autowired
    public TestMessage() {
    }

    public TestMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

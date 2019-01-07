package com.demo.websocket.controller;

import com.demo.websocket.domain.TestMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@RestController
public class SimpleRestController {
    private final static Logger logger = LoggerFactory.getLogger(SimpleRestController.class);

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public TestMessage test(@RequestBody @Valid TestMessage message) {
        logger.info("http:" + message.getMessage());
        return new TestMessage("simple http success");
    }

    @RequestMapping(value = "/ws/test", method = RequestMethod.POST)
    public TestMessage wsTest(@RequestBody @Valid TestMessage message) {
        logger.info("ws:" + message.getMessage());
        return new TestMessage("simple ws success");
    }
}

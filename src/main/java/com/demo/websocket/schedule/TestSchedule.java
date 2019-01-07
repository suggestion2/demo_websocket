package com.demo.websocket.schedule;

import com.demo.websocket.controller.SimpleRestController;
import com.demo.websocket.domain.SessionMapService;
import com.demo.websocket.domain.TestMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
public class TestSchedule {

    private final static Logger logger = LoggerFactory.getLogger(TestSchedule.class);

    @Autowired
    private SessionMapService sessionMapService;

    @Scheduled(cron="0/10 * * * * *")
//    @Scheduled(cron="0 * * * * *")
    public void autoSendMsg(){
        List<WebSocketSession> sessionList = sessionMapService.getAll();
        if(Objects.isNull(sessionList) || sessionList.size() == 0){
            return;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        sessionList.forEach(s->{
            try {
                s.sendMessage(new TextMessage(objectMapper.writeValueAsString(new TestMessage("new reply ws"))));
            } catch (IOException e) {
                logger.info("schedule error:" + e.getMessage());
            }
        });
    }
}

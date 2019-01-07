package com.demo.websocket.handler;

import com.demo.websocket.controller.SimpleRestController;
import com.demo.websocket.domain.SessionMapService;
import com.demo.websocket.domain.TestMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class TestHandler extends TextWebSocketHandler {
    private final static Logger logger = LoggerFactory.getLogger(TestHandler.class);

    @Autowired
    private SessionMapService sessionMapService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("message:" + message.getPayload());
        for(String attr : session.getAttributes().keySet()){
            logger.info("attribute:" + attr);
        }
        for(String attr : session.getHandshakeHeaders().keySet()){
            logger.info("hsHeader:" + attr + (attr.contains("cookie")?",value:" + session.getHandshakeHeaders().get(attr) : ""));
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TextMessage replyMsg = new TextMessage(objectMapper.writeValueAsString(new TestMessage("reply ws")));
        session.sendMessage(replyMsg);
        sessionMapService.put(session);
        super.handleTextMessage(session, message);
    }
}

package com.demo.websocket.domain;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Component
public class SessionMapService {
    private final ConcurrentHashMap<String,WebSocketSession> map = new ConcurrentHashMap<>();

    public void put(WebSocketSession session){
        map.put(session.getId(),session);
    }

    public WebSocketSession get(String id){
        return map.get(id);
    }

    public List<WebSocketSession> getAll(){
        return new ArrayList<>(map.values());
    }

}

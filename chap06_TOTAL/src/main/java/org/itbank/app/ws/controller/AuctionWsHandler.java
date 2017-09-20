package org.itbank.app.ws.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component("auctionws")
public class AuctionWsHandler extends TextWebSocketHandler{
	Map<String, WebSocketSession> users = new HashMap<>();;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Map<String, Object> hs = session.getAttributes();
		String id = (String) hs.get("auth");
		users.put(id, session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		Map<String, Object> hs = session.getAttributes();
		String id = (String) hs.get("auth");
		users.remove(id);
	}
	
	public void sendMessageToUser(String id, String msg) {
		Set<String> set = users.keySet();
		for(String s : set) {
			if(s != id) {
				try {
					users.get(s).sendMessage(new TextMessage(msg));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

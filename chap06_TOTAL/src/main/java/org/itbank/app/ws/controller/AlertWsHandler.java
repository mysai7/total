package org.itbank.app.ws.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component("aws")
public class AlertWsHandler extends TextWebSocketHandler{
	List<WebSocketSession> list;
	// �� Handler ��ü�� ���ؼ� ����Ǿ��ִ� ��� WebSocketSession �� �޼����� ������ �ִ�
	// �޼��带 �ϳ� �߰��صνð�,
	public void sendMessage(String message) {
		for(WebSocketSession s : list) {
			try {
				s.sendMessage(new TextMessage(message));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@PostConstruct
	public void alertWsInit() {
		list = new ArrayList<>();
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		list.add(session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		list.remove(session);
	}
}

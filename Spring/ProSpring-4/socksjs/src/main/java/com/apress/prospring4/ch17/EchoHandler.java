package com.apress.prospring4.ch17;

import java.io.IOException;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler {

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws IOException {
		session.sendMessage(new TextMessage(textMessage.getPayload()));
	}
	
}

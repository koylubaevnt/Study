package com.koylubaevnt.own.others;

import java.nio.channels.SocketChannel;

public class ServerDataEvent {

	NioServer server;
	public SocketChannel socket;
	public byte[] data;
	
	public ServerDataEvent(NioServer server, SocketChannel socket, byte[] data) {
		super();
		this.server = server;
		this.socket = socket;
		this.data = data;
	}
	
	
}

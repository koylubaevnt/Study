package com.koylubaevnt.own.others;

import java.nio.channels.SocketChannel;

public class ChangeRequest {

	public static final int CHANGEOPS = 2;
	public int type;
	public SocketChannel socket;
	public int ops;
	
	public ChangeRequest(SocketChannel socket, int type, int ops) {
		this.socket = socket;
		this.type = type;
		this.ops = ops;
	}

	

}

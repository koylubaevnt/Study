package com.koylubaevnt.own.others;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

	public static final int PORT = 19000;
	
	private Socket s;
	private int num;
	
	
	
	public Server(Socket s, int num) {
		this.s = s;
		this.num = num;
		
		setDaemon(true);
		setPriority(NORM_PRIORITY);
		start();
	}


	@Override
	public void run() {
		System.out.println("Accepted. " + s.getInetAddress());
		try(InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream()) {
				
			byte[] data = new byte[32 * 1024];
			int readBytes = in.read(data);
				
			String line = new String(data, 0, readBytes);
			System.out.printf("Client> %s", line);
				
			out.write(line.getBytes());
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		int i = 0;
		try(ServerSocket serverSocket = new ServerSocket(PORT)) {
			while(true) {
				System.out.println("Started, waiting for connection");
				new Server(serverSocket.accept(), i++);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

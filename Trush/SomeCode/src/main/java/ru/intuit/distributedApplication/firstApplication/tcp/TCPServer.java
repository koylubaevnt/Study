package ru.intuit.distributedApplication.firstApplication.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static final int PORT = 7896;

	public static void main(String[] args) {
		System.out.println("TCPServer: Start server on port " + PORT);
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			
			while(true) {
				Socket socket = serverSocket.accept();
				Connection connection = new Connection(socket);
			}
			//Connection c = new C
		} catch (IOException e ) {
			System.out.println("TCPServer: Error " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("TCPServer: Stop server.");
	}
	
}

class Connection extends Thread {
	
	DataInputStream in;
	DataOutputStream out;
	Socket socket;
	public Connection(Socket socket) {
		this.socket = socket;
		try {
			this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			this.start();
		} catch (IOException e) {
			System.out.println("Connection: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		String data;
		try {
			data = in.readUTF();
			System.out.println("TCPServer: Recieved: " + data);
			data = "TCPServer recived data from you: " + data;
			System.out.println("TCPServer: Send: " + data);
			out.writeUTF(data);
		} catch (IOException e) {
			try {
				out.writeUTF("TCPServer error: " + e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}

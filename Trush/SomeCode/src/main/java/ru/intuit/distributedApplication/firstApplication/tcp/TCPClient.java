package ru.intuit.distributedApplication.firstApplication.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPClient {

	public static final int PORT = 7896;

	public static void main(String[] args) {
		try (Socket socket = new Socket(args[1], PORT);
			 DataInputStream in = new DataInputStream(socket.getInputStream());
			 DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
			out.writeUTF(args[0]);
			String data = in.readUTF();
			System.out.println("Recived: " + data);
		} catch (IOException e ) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
}

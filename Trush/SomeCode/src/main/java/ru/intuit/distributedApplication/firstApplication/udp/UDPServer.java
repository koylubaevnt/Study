package ru.intuit.distributedApplication.firstApplication.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {

	public static final int PORT = 6789;
	
	public static void main(String[] args) {
		/*
		 * Программа (сервер) создает сокет и обслуживает запросы клиента 
		 *  
		 */
		System.out.println("UDPServer: Start server");
		try(DatagramSocket socket = new DatagramSocket(6789)) {
			byte[] buffer;
			while(true) {
				buffer = new byte[1000];
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				socket.receive(request);
				String line = new String(request.getData());
				if(line.trim().equals("StopServer")) {
					String s = "Server is stopped";
					DatagramPacket reply = new DatagramPacket(s.getBytes(), s.length(), request.getAddress(), request.getPort());
					socket.send(reply);
					break;
				} else {
					System.out.println("UDPServer: Sending " + line);
				}
				DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
				socket.send(reply);
			}
		} catch (IOException e) {
			System.out.println("UDPServer: Error " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("UDPServer: Stop server");
	}
	
}

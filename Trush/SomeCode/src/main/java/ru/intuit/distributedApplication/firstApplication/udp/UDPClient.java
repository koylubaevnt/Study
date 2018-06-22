package ru.intuit.distributedApplication.firstApplication.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

	public static final int PORT = 6789;
	
	public static void main(String[] args) {
		/*
		 * Программа создает сокет, соединяется с сервером (порт 6789), пересылает 
		 * ему сообщение и ждет ответа
		 *  
		 */
		try(DatagramSocket socket = new DatagramSocket()) {
			byte[] message = args[0].getBytes();
			InetAddress host = InetAddress.getByName(args[1]);
			DatagramPacket request = new DatagramPacket(message, args[0].length(), host, PORT);
			socket.send(request);
			
			byte[] buffer = new byte[1000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
			socket.receive(reply);
			System.out.println("Reply: " + new String(reply.getData()));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
}

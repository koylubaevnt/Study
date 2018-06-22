package com.koylubaevnt.own.others;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class NioClient {
	
	static final int PORT = 9090;
	static final String ADDRESS = "localhost";
	private ByteBuffer buffer = ByteBuffer.allocate(16);
	
	public static void main(String[] args) throws Exception {
		new NioClient().run();
	}
	
	private void run() throws Exception {
		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false);
		Selector selector = Selector.open();
		channel.register(selector, SelectionKey.OP_CONNECT);
		channel.connect(new InetSocketAddress(ADDRESS, PORT));
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(2);
		new Thread(() -> {
			Scanner scanner = new Scanner(System.in);
			while(true) {
				String line = scanner.nextLine();
				if ("q".equals(line)) {
					System.exit(0);
				}
				try {
					queue.put(line);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				SelectionKey key = channel.keyFor(selector);
				key.interestOps(SelectionKey.OP_WRITE);
				selector.wakeup();
			}
			
		}).start();
		while(true) {
			selector.select();
			for(SelectionKey selectionKey : selector.selectedKeys()) {
				if(selectionKey.isConnectable()) {
					channel.finishConnect();
					selectionKey.interestOps(SelectionKey.OP_WRITE);
				} else if(selectionKey.isReadable()) {
					buffer.clear();
					int readBytes = channel.read(buffer);
					if(readBytes != 0) {
						System.out.println("Recived = " + new String(buffer.array()));
					}
				} else if(selectionKey.isWritable()) {
					String line = queue.poll();
					if(line != null) {
						channel.write(ByteBuffer.wrap(line.getBytes()));
					}
					selectionKey.interestOps(SelectionKey.OP_READ);
				}
			}
		}
	}
	
	
}

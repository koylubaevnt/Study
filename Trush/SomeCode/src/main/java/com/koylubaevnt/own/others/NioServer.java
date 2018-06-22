package com.koylubaevnt.own.others;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NioServer {

	static final int PORT = 9090;
	static final String ADDRESS = "localhost";
	
	private Selector selector;
	private ByteBuffer buffer = ByteBuffer.allocate(8192);
	private EchoWorker worker = new EchoWorker();
	private final List<ChangeRequest> changeRequests = new LinkedList<>();
	private final Map<SocketChannel, List<ByteBuffer>> pendingData = new HashMap<>();

	private NioServer() throws IOException {
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		InetSocketAddress isa = new InetSocketAddress(ADDRESS, PORT);
		serverChannel.socket().bind(isa);
		selector = SelectorProvider.provider().openSelector();
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		new Thread(worker).start();
	}
	
	public static void main(String[] args) throws Exception {
		new NioServer().run();

	}
	
	private void run() throws Exception {
		while(true) {
			synchronized (changeRequests) { 
				for(ChangeRequest change : changeRequests) {
					switch (change.type) { 
						case ChangeRequest.CHANGEOPS:
							SelectionKey key = change.socket.keyFor(selector);
							key.interestOps(change.ops);
							break;
						default:
					}
				}
				changeRequests.clear();
			}
			selector.select();
			Iterator<SelectionKey> selectedKey = selector.selectedKeys().iterator();
			while(selectedKey.hasNext()) {
				SelectionKey key = selectedKey.next();
				selectedKey.remove();
				if(!key.isValid()) {
					continue;
				}
				if(key.isAcceptable()) {
					accept(key);
				} else if(key.isReadable()) {
					read(key);
				} else if(key.isWritable()) {
					write(key);
				} 
			}
		}
	}

	private void write(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		synchronized (pendingData) {
			List<ByteBuffer> queue = pendingData.get(socketChannel);
			while(!queue.isEmpty()) {
				ByteBuffer buf = queue.get(0);
				socketChannel.write(buf);
				if(buf.remaining() > 0) {
					break;
				}
				System.out.println("Send echo = " + new String(queue.get(0).array()));
				queue.remove(0);
			}
			if(queue.isEmpty()) {
				key.interestOps(SelectionKey.OP_READ);
			}
		}
		
	}

	private void read(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		buffer.clear();
		int numRead = socketChannel.read(buffer);
		worker.processData(this, socketChannel, buffer.array(), numRead);
	}

	private void accept(SelectionKey key) throws IOException {
		ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
		SocketChannel socketChannel = serverSocketChannel.accept();
		socketChannel.configureBlocking(false);
		socketChannel.register(selector, SelectionKey.OP_READ);
		
	}

	public void send(SocketChannel socket, byte[] data) {
		synchronized (changeRequests) {
			changeRequests.add(new ChangeRequest(socket, ChangeRequest.CHANGEOPS, SelectionKey.OP_WRITE));
			synchronized (pendingData) {
				List<ByteBuffer> queue = pendingData.get(socket);
				if(queue == null) {
					queue = new ArrayList<>();
					pendingData.put(socket, queue);
				}
				queue.add(ByteBuffer.wrap(data));
			}
			selector.wakeup();
		}
	}	
}

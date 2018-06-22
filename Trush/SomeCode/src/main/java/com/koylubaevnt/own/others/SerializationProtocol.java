package com.koylubaevnt.own.others;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class SerializationProtocol implements Protocol {

	@Override
	public byte[] encode(Message msg) throws ProtocolException {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutput out = new ObjectOutputStream(baos)) {
			out.writeObject(msg);
			
			byte[] objData = baos.toByteArray();
			int size = objData.length;
			
			ByteBuffer buf = ByteBuffer.allocate(size + 4);
			buf.putInt(size);
			buf.put(objData);
			return buf.array();
		} catch (IOException e) {
			throw new ProtocolException("Failed to encode message", e);
		}
	}

	@Override
	public Message decode(byte[] data) throws ProtocolException {
		ByteBuffer buf = ByteBuffer.wrap(data);
		int size = buf.getInt();
		if(size != data.length - 4) {
			throw new ProtocolException(String.format("Invalid data. Expected size: %d, got: %d", data.length - 4, size));
		}
		byte[] objData = new byte[size];
		buf.get(objData);
		
		try (ByteArrayInputStream bais = new ByteArrayInputStream(objData);
				ObjectInput in = new ObjectInputStream(bais)) {
			return (Message) in.readObject();
		} catch (IOException e) {
			throw new ProtocolException("Failed to decode message", e);
		} catch (ClassNotFoundException e) {
			throw new ProtocolException("No class found", e);
		}
	}

	public static void main(String[] args) throws ProtocolException {
		Message message = new Message();
		message.setId(100L);
		message.setSender(99L);
		
		SerializationProtocol protocol = new SerializationProtocol();
		byte[] array = protocol.encode(message);
		
		System.out.println(protocol.decode(array));
	}
	
}

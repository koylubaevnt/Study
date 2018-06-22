package com.koylubaevnt.own.others;

public interface Protocol {

	byte[] encode(Message msg) throws ProtocolException;
	
	Message decode(byte[] data) throws ProtocolException;
}

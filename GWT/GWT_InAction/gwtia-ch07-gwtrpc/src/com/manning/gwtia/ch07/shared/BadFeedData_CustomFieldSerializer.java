/**
 * 
 */
package com.manning.gwtia.ch07.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * @author KojlubaevNT
 *
 */
public class BadFeedData_CustomFieldSerializer {

	/*Сериализация объекта в поток*/
	public static void serialize(SerializationStreamWriter ssw, BadFeedData instance) throws SerializationException {
		ssw.writeObject(instance.getCreatedAt());
		ssw.writeString(instance.getText());
	}
	
	/*Создание инстанса объекта из потока*/
	public static BadFeedData instantiate(SerializationStreamReader ssr) throws SerializationException {
		return new BadFeedData((Date) ssr.readObject());
	}
	
	/*Десериализация потока в объект*/
	public static void deserialize(SerializationStreamReader ssr, BadFeedData instance) throws SerializationException {
		instance.setText(ssr.readString());
	}
	
}

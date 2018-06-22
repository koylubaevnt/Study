package com.manning.gwtia.ch08.v1.server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CEM {

	static ConcurrentHashMap<Long, Contact> entities = new ConcurrentHashMap<>();
	static AtomicLong index = new AtomicLong();
	
	public static void persist(Contact entity) {
		if(entity.getId() == null) entity.setId(index.incrementAndGet());
		if(entity.getVersion() == null) {
			entity.setVersion(1);
		} else {
			entity.setVersion(entity.getVersion() + 1);
		}
		entities.put(entity.getId(), entity);
	}
	
	public static Contact fetch(Long id) {
		System.out.println("fetch: " + id);
		return copy(entities.get(id));
	}
	
	public static boolean delete(Long id) {
		return entities.remove(id) != null;
	}
	
	public static List<Contact> list() {
		List<Contact> result = new ArrayList<>();
		for(Contact e : entities.values()) {
			result.add(e);
		}
		return result;
	}
	
	private static Contact copy(Contact c) {
		System.out.println("copy: " + c);
		if(c == null) return null;
		System.out.println("copy: not null? " + c);
		Contact copy = new Contact();
		copy.setId(c.getId());
		copy.setVersion(c.getVersion());
		copy.setName(c.getName());
		copy.setEmail(c.getEmail());
		copy.setPhones(c.getPhones());
		copy.setNote(c.getNote());
		return copy;
	}
}

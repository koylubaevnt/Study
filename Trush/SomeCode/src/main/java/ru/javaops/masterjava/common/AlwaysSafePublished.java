package ru.javaops.masterjava.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AlwaysSafePublished {
	
	private final Map<String, Collection> map = new HashMap();

	public AlwaysSafePublished() {
		Collection c = new ArrayList();
		c.add("a");
		c.add("A");
		map.put("1", c);
	}

	public int number() {
		return map.get("1").size();
	}
}

package com.brysekkel.typeinfo.containers;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SlowMap2<K,V> extends AbstractMap<K, V> {

	private List<K> keys = new ArrayList<>();
	private List<V> values = new ArrayList<>();
	transient Set<Map.Entry<K, V>> entrySet = new EntrySet<>();
	
	final class EntrySet<K,V> extends AbstractSet<Map.Entry<K,V>> {
        @Override
		public final int size() {
        	return keys.size(); 
        }
        @Override
		public final void clear() { 
        	keys.clear(); 
        	values.clear();
        }
        @Override
		public final Iterator<Map.Entry<K,V>> iterator() {
            return new Iterator<Map.Entry<K,V>>() {
            	private int index = -1;
				@Override
				public boolean hasNext() {
					return index < keys.size() - 1;
				}

				@Override
				public java.util.Map.Entry<K, V> next() {
					index++;
					return new MapEntry(keys.get(index), values.get(index));
				}

				@Override
				public void remove() {
					keys.remove(index);
					values.remove(index--);
				}
			};
        }
    }
	
	@Override
	public V put(K key, V value) {
		V oldValue = get(key);
		if(!keys.contains(key)) {
			keys.add(key);
			values.add(value);
		} else {
			values.set(keys.indexOf(key), value);
		}
		return oldValue;
	}
	
	@Override
	public V get(Object key) {
		if(!keys.contains(key)) {
			return null;
		}
		return values.get(keys.indexOf(key));
	}
		
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return entrySet;  
	}
	
	
	public static void main(String[] args) {
		SlowMap2<String, String> m = new SlowMap2<>();
		m.putAll(Countries.capitals(15));
		System.out.println(m);
		System.out.println(m.get("BURUNDI"));
		System.out.println(m.entrySet());
		
		Maps.test(new HashMap<>());
		Maps.test(new SlowMap2<>());
		
	}
}

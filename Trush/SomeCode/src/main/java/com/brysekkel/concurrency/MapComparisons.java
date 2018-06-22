package com.brysekkel.concurrency;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.brysekkel.concurrency.ListTest.Reader;
import com.brysekkel.concurrency.ListTest.Writer;
import com.brysekkel.typeinfo.arrays.CountingGenerator;
import com.brysekkel.typeinfo.containers.MapData;

abstract class MapTest extends Tester<Map<Integer, Integer>> {
	
	public MapTest(String testId, int nReaders, int nWriters) {
		super(testId, nReaders, nWriters);
	}
	
	class Reader extends Tester.TestTask {
		long result = 0;
		
		@Override
		void test() {
			for(long i = 0; i < testCycles; i++)
				for(int index = 0; index < containersSize; index++)
					result += testContainer.get(index);
		}
		
		@Override
		void putResults() {
			readResult += result;
			readTime += duration;
		}
	}
	
	class Writer extends Tester.TestTask {
		
		@Override
		void test() {
			for(long i = 0; i < testCycles; i++)
				for(int index = 0; index < containersSize; index++)
					testContainer.put(index, writeData[index]);
		}
		
		@Override
		void putResults() {
			writeTime += duration;
		}
	}

	@Override
	void startReadersAndWriters() {
		for(int i = 0; i < nReareds; i++)
			exec.execute(new Reader());
		for(int i = 0; i < nWriters; i++)
			exec.execute(new Writer());
	}
}

class SynchronizedHashMapTest extends MapTest {
	
	@Override
	Map<Integer, Integer> containerInitializer() {
		return Collections.synchronizedMap(
				new HashMap<>(
						MapData.map(
								new CountingGenerator.Integer(),
								new CountingGenerator.Integer(), 
								containersSize)));
	}
	
	public SynchronizedHashMapTest(int nReaders, int nWriters) {
		super("Synched HashMap", nReaders, nWriters);
	}
}

class ConcurrentHashMapTest extends MapTest {
	
	@Override
	Map<Integer, Integer> containerInitializer() {
		return new ConcurrentHashMap<>(
						MapData.map(
								new CountingGenerator.Integer(),
								new CountingGenerator.Integer(), 
								containersSize));
	}
	
	public ConcurrentHashMapTest(int nReaders, int nWriters) {
		super("ConcurrentHashMap", nReaders, nWriters);
	}
}

public class MapComparisons {

	public static void main(String[] args) {
		Tester.initMain(args);
		new SynchronizedHashMapTest(10, 0);
		new SynchronizedHashMapTest(9, 1);
		new SynchronizedHashMapTest(5, 5);
		new ConcurrentHashMapTest(10, 0);
		new ConcurrentHashMapTest(9, 1);
		new ConcurrentHashMapTest(5, 5);
		Tester.exec.shutdown();
	}

}

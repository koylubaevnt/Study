package ru.intuit.deepjava.firstIndependentWork.resourceSystem.base;

import java.util.Iterator;

public interface VirtuaFileSystem {

	boolean isExist(String path);
	
	boolean isDirectory(String path);
	
	String getAbsolutePath(String file);

	byte[] getBytes(String file);
	
	String getUTF8Text(String file);
	
	Iterator<String> getIterator(String startDirectory);
}

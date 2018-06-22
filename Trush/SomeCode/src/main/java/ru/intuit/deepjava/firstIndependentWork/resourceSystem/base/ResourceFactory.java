package ru.intuit.deepjava.firstIndependentWork.resourceSystem.base;

public interface ResourceFactory {

	void addResource(String file, Resource resource);
	
	Resource getResource(String file);
	
}

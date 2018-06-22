package ru.intuit.deepjava.firstIndependentWork.resourceSystem;

import ru.intuit.deepjava.firstIndependentWork.resourceSystem.base.Resource;
import ru.intuit.deepjava.firstIndependentWork.resourceSystem.base.VirtuaFileSystem;

public class ResourceFactroy {

	private ResourceFactroy() {
		
	}
	
	public ResourceFactroy getInstance() {
		return ResourceFactroyHelper.INSTANCE;
	}
	
	public Resource getResource(String path) {
		VirtuaFileSystem virtuaFileSystem = new VirtualFileSystemImpl(path);
		if(virtuaFileSystem.isExist(path)) {
			return null;//virtuaFileSystem.
		} else {
			return null;
		}
	}
	
	private static class ResourceFactroyHelper {
		private static final ResourceFactroy INSTANCE = new ResourceFactroy();
	}
}

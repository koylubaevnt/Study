package com.apress.prospring4.ch11.l1113;

import java.util.concurrent.Future;

public interface AsyncService {

	void asyncTask();
	Future<String> asyncWithReturn(String name);
	
}

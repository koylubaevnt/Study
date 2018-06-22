package com.apress.prospring4.ch11.l1113;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service("asyncService")
public class AsyncServiceImpl implements AsyncService {

	final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);
	
	@Async
	@Override
	public void asyncTask() {
		logger.info("Start execution of async. task");
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("Complete execution of async. task");
	}

	@Async
	@Override
	public Future<String> asyncWithReturn(String name) {
		logger.info("Start execution of async. task with returt");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("Complete execution of async. task with returt");
		return new AsyncResult<String>("Hello: " + name);
	}

}

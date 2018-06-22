package ru.intuit.deepjava.firstIndependentWork.utils;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

public class TimeService {
	
	private Timer timer;
	
	private TimeService() {
		
	}
	
	public static TimeService getInstance() {
		return TimeServiceHelper.INSTANCE;
	}
	
	private static class TimeServiceHelper {
		private static final TimeService INSTANCE = new TimeService();
	}

	public void start() {
		timer = new Timer();
		//System.out.println("Start timer");
	}

	public void stop() {
		if(timer != null) {
			timer.cancel();
		}
		//System.out.println("Stop timer");
	}
	
	public boolean scheduleTask(TimerTask task, long delay) {
		if(timer != null) {
			timer.schedule(task, delay);
			return true;
		}
		return false;
	}
	
	@Test
	public static void testTimeService() {
		int timeMs = 5000;
		System.out.println("Start main");
		TimeService.getInstance().start();
		TimeService.getInstance().scheduleTask(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("Timer run!");
				TimeHelper.sleep(1000);
				TimeService.getInstance().stop();
			}
		}, timeMs);
		System.out.println("End main");
	}
}

package ru.tproger.java8.threads;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Сервис-исполнитель (<code>ExecutorService</code>) — высокоуровневая замена работе с потоками напрямую. 
 * Исполнители выполняют задачи асинхронно и обычно используют пул потоков, так что нам не надо 
 * создавать их вручную. Все потоки из пула будут использованы повторно после выполнения задачи, 
 * а значит, мы можем создать в приложении столько задач, сколько хотим, используя один исполнитель.
 *
 * Работу исполнителей надо завершать явно. Для этого в интерфейсе <code>ExecutorService</code> есть два метода: 
 * <code>shutdown()</code>, который ждет завершения запущенных задач, и 
 * <code>shutdownNow()</code>, который останавливает исполнитель немедленно.
 * 
 * @author KojlubaevNT
 *
 */
public class ExecutorServiceDemo {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Введите номер версии метода: ");
		int version = scanner.nextInt();
		switch (version) {
		case 1:
			firstVersion();
			break;
		default:
			System.out.println("Не правильный номер версии метода");
			break;
		}
	}
	
	
	public static void firstVersion() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> {
		    String threadName = Thread.currentThread().getName();
		    System.out.println("Hello " + threadName);
		});
		
		/*Исполнитель пытается завершить работу, ожидая завершения запущенных задач в течение 
		 * определенного времени (5 секунд). По истечении этого времени он останавливается, 
		 * прерывая все незавершенные задачи.
		 */
		try {
		    System.out.println("attempt to shutdown executor");
		    executor.shutdown();
		    executor.awaitTermination(5, TimeUnit.SECONDS);
		}
		catch (InterruptedException e) {
		    System.err.println("tasks interrupted");
		}
		finally {
		    if (!executor.isTerminated()) {
		        System.err.println("cancel non-finished tasks");
		    }
		    executor.shutdownNow();
		    System.out.println("shutdown finished");
		}
	}
}

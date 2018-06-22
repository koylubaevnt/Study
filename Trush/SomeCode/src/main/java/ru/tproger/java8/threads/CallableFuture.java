package ru.tproger.java8.threads;

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * <code>Callable</code> — это также функциональный интерфейс, но, в отличие от <code>Runnable</code>, 
 * он может возвращать значение.
 * Callable-задачи также могут быть переданы исполнителям.
 *  
 * @author KojlubaevNT
 *
 */

public class CallableFuture {

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Введите номер версии метода: ");
		int version = scanner.nextInt();
		switch (version) {
		case 1:
			firstVersion();
			break;
		case 2:
			secondVersion();
			break;
		default:
			System.out.println("Не правильный номер версии метода");
			break;
		}
	}

	public static void firstVersion() throws InterruptedException, ExecutionException {
		Callable<Integer> task = () -> {
		    try {
		        TimeUnit.SECONDS.sleep(1);
		        return 123;
		    }
		    catch (InterruptedException e) {
		        throw new IllegalStateException("task interrupted", e);
		    }
		};
		
		/* После отправки задачи исполнителю мы сначала проверяем, завершено ли ее выполнение, с 
		 * помощью метода isDone(). Поскольку задача имеет задержку в одну секунду, прежде чем 
		 * вернуть число, она еще не завершена. Вызов метода get() блокирует 
		 * поток и ждет завершения задачи, а затем возвращает результат ее выполнения. 
		 * После future.isDone() вернет true.
		 * 
		 */
		ExecutorService executor = Executors.newFixedThreadPool(1);
		Future<Integer> future = executor.submit(task);
		 
		System.out.println("future done? " + future.isDone());
		 
		Integer result = future.get();
		 
		System.out.println("future done? " + future.isDone());
		System.out.print("result: " + result);
	}
	
	public static void secondVersion() throws InterruptedException, ExecutionException, TimeoutException {
		Callable<Integer> task = () -> {
		    try {
		        TimeUnit.SECONDS.sleep(2);
		        return 123;
		    }
		    catch (InterruptedException e) {
		        throw new IllegalStateException("task interrupted", e);
		    }
		};
		
		ExecutorService executor = Executors.newFixedThreadPool(1);
		Future<Integer> future = executor.submit(task);
		 
		System.out.println("future done? " + future.isDone());
		 /*
		  * Любой вызов метода future.get() блокирует поток до тех пор, пока задача не будет 
		  * завершена. В наихудшем случае выполнение задачи не завершится никогда, блокируя ваше 
		  * приложение. Избежать этого можно, передав таймаут.
		  */
		Integer result = future.get(1, TimeUnit.SECONDS);
		 
		System.out.println("future done? " + future.isDone());
		System.out.print("result: " + result);
	}
}

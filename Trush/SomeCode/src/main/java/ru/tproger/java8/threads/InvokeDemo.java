package ru.tproger.java8.threads;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

/**
 * Исполнители могут принимать список задач на выполнение с помощью метода invokeAll(), который 
 * принимает коллекцию callable-задач и возвращает список из Future.
 * 
 * Другой способ отдать на выполнение несколько задач — метод invokeAny(). Он работает немного 
 * по-другому: вместо возврата Future он блокирует поток до того, как завершится хоть одна задача, 
 * и возвращает ее результат.
 * 
 * @author KojlubaevNT
 *
 */

public class InvokeDemo {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Введите номер версии метода: ");
		int version = scanner.nextInt();
		switch (version) {
		case 1:
			firstVersion();
			break;
		/*
		case 2:
			secondVersion();
			break;
			*/
		default:
			System.out.println("Не правильный номер версии метода");
			break;
		}
	}
	
	public static void firstVersion() throws InterruptedException {
		ExecutorService executor = Executors.newWorkStealingPool();
		 
		List<Callable<String>> callables = Arrays.asList(
		        () -> "task1",
		        () -> "task2",
		        () -> "task3");
		/*
		 * Прошлись по всем задачам и вывели их результат на консоль, испльзуя StreamAPI
		 */
		executor.invokeAll(callables)
		    .stream()
		    .map(future -> {
		        try {
		            return future.get();
		        }
		        catch (Exception e) {
		            throw new IllegalStateException(e);
		        }
		    })
		    .forEach(System.out::println);
	}
	/*
	public static void secondVersion() throws InterruptedException {
		Callable callable(String result, long sleepSeconds) {
		    return () -> {
		        TimeUnit.SECONDS.sleep(sleepSeconds);
		        return result;
		    };
		}
		
	}
	*/
	
}

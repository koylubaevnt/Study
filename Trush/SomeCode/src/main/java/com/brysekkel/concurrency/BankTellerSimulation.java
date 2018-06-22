package com.brysekkel.concurrency;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//Объекты, доступные только для чтения, не требуют синхронизации
class Customer {
	private final int serviceTime;
	public Customer(int tm) {
		serviceTime = tm;
	}
	public int getServiceTime() {
		return serviceTime;
	}
	@Override
	public String toString() {
		return "[" + serviceTime + "]";
	}
}

//Очередь клиентов умеет выводить свое состояние
class CustomerLine extends ArrayBlockingQueue<Customer> {
	public CustomerLine(int maxLineSize) {
		super(maxLineSize);
	}
	@Override
	public String toString() {
		if(this.size() == 0)
			return "[Empty]";
		StringBuilder result = new StringBuilder();
		for(Customer customer : this)
			result.append(customer);
		return result.toString();
	}
}

//Случайное добавление клиентв в очередь
class CustomerGenerator implements Runnable {
	private CustomerLine customers;
	private static Random random = new Random(47);
	
	public CustomerGenerator(CustomerLine line) {
		customers = line;
	}
	
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(random.nextInt(300));
				customers.put(new Customer(random.nextInt(1000)));
			}
		} catch (InterruptedException e) {
			System.out.println("CustomerGenerator interrupted");
		}
		System.out.println("CustomerGenerator terminating");
	}
	
}

class Teller implements Runnable, Comparable<Teller> {
	private static int counter = 0;
	private final int id = counter++;
	private int customersServed = 0; //Клиенты, обслуженные за смену
	private CustomerLine customers;
	private boolean servingCustomerLine = true;
	
	public Teller(CustomerLine line) {
		customers = line;
	}
	
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()) {
				Customer customer = customers.take();
				TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
				synchronized (this) {
					customersServed++;
					while (!servingCustomerLine) {
						wait();
					}
				}
			}
		} catch (InterruptedException e) {
			System.out.println(this + " interrupted");
		}
		System.out.println(this + " terminating");
	}
	
	public synchronized void doSomethingElse() {
		customersServed = 0;
		servingCustomerLine = false;
	}
	
	public synchronized void serveCustomerLine() {
		assert !servingCustomerLine:"already serving: " + this;
		servingCustomerLine = true;
		notifyAll();
	}
	
	@Override
	public String toString() {
		return "Teller " + id + " ";
	}
	
	public String shortString() {
		return "T " + id;
	}
	//Используется приоритетной очередью
	@Override
	public synchronized int compareTo(Teller o) {
		return customersServed < o.customersServed ? -1 :
			(customersServed == o.customersServed ? 0 : 1);
	}
}

class TellerManager implements Runnable {
	private ExecutorService exec;
	private CustomerLine customers;
	private PriorityQueue<Teller> workingTellers = new PriorityQueue<>();
	private Queue<Teller> tellersDoingOtherThings = new LinkedList<>();
	private int adjustmentPeriod;
	private static Random random = new Random(47);
	
	public TellerManager(ExecutorService e, CustomerLine line, int adjustment) {
		exec = e;
		customers = line;
		adjustmentPeriod = adjustment;
		//начать с одного кассира
		Teller teller = new Teller(customers);
		exec.execute(teller);
		workingTellers.add(teller);
	}
	public void adjustTellerNumber() {
		//Если очередь длинная, добавить кассира
		if(customers.size() / workingTellers.size() > 2) {
			//Если кассиры отдыхают, вернуть одного
			if(tellersDoingOtherThings.size() > 0) {
				Teller teller = tellersDoingOtherThings.remove();
				teller.serveCustomerLine();
				workingTellers.offer(teller);
				return;
			}
			//Иначе создать нового кассира
			Teller teller = new Teller(customers);
			exec.execute(teller);
			workingTellers.add(teller);
			return;
		}
		//Если очередь короткая то убрать кассира
		if(workingTellers.size() > 1 && customers.size() / workingTellers.size() < 2)
			reassignOneTeller();
		//Если очереди нет, достаточно одного кассира
		if(customers.size() == 0)
			while (workingTellers.size() > 1) {
				reassignOneTeller();
			}
	}
	
	//Отправить кассира на другую работу
	private void reassignOneTeller() {
		Teller teller = workingTellers.poll();				
		teller.doSomethingElse();
		tellersDoingOtherThings.offer(teller);
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
				adjustTellerNumber();
				System.out.print(customers + " { ");
				for(Teller teller : workingTellers)
					System.out.print(teller.shortString() + " ");
				System.out.println("}");
			}
		} catch (InterruptedException e) {
			System.out.println(this + "  interrupted");
		}
		System.out.println(this + "  terminating");
	}
	
	@Override
	public String toString() {
		return "TellerManager ";
	}
}

public class BankTellerSimulation {

	static final int MAX_LINE_SIZE = 50;
	static final int ADJUSTMENT_PERIOD = 1000;
	
	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		//Если очередь длинная, то клиенты уходят
		CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);
		exec.execute(new CustomerGenerator(customers));
		//Менеджер добавляет и удаляет кассиров по необходимости
		exec.execute(new TellerManager(exec, customers, ADJUSTMENT_PERIOD));
		if(args.length > 0)
			TimeUnit.SECONDS.sleep(new Integer(args[0]));
		else {
			System.out.println("Press 'Enter' to quit");
			System.in.read();
		}
		exec.shutdownNow();
	}

}

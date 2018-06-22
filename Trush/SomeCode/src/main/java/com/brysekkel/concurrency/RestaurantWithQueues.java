package com.brysekkel.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import com.brysekkel.typeinfo.enumerated.menu.Course;
import com.brysekkel.typeinfo.enumerated.menu.Food;

class Order {
	private static int counter = 0;
	private final int id = counter++;
	private final Customer2 customer;
	private final WaitPerson2 waitPerson;
	private final Food food;
	
	public Order(Customer2 customer, WaitPerson2 waitPerson, Food food) {
		this.customer = customer;
		this.waitPerson = waitPerson;
		this.food = food;
	}
	
	public Food item() {
		return food;
	}
	
	public Customer2 getCustomer() {
		return customer;
	}
	
	public WaitPerson2 getWaitPerson() {
		return waitPerson;
	}
	
	@Override
	public String toString() {
		return "Order: " + id + " item: " + food + 
				" for: " + customer +
				" served by: " + waitPerson;
	}
}

class Plate {
	private final Order order;
	private final Food food;
	
	public Plate(Order order, Food food) {
		this.food = food;
		this.order = order;
	}
	public Order getOrder() {
		return order;
	}
	public Food getFood() {
		return food;
	}
	@Override
	public String toString() {
		return food.toString();
	}
}

class Customer2 implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private final WaitPerson2 waitPerson;
	
	//Посетитель может получать только одно блюдо за раз
	private SynchronousQueue<Plate> placeSetting =
			new SynchronousQueue<>();
	
	public Customer2(WaitPerson2 waitPerson) {
		this.waitPerson = waitPerson;
	}
	
	public void deliver(Plate p) throws InterruptedException {
		placeSetting.put(p);//Блокируется, если посетитель не закончил с предыдущим блюдом
	}
	
	@Override
	public void run() {
		for(Course course : Course.values()) {
			Food food = course.randomSelection();
			try {
				waitPerson.placeOrder(this, food);
				//Блокируется до получения блюда
				System.out.println(this + " eating " + placeSetting.take());
			} catch (InterruptedException e) {
				System.out.println(this + " waiting for " + course + " interrupted");
				break;
			}
		}
		System.out.println(this + " finished meal, leaving");
	}
	
	@Override
	public String toString() {
		return "Customer " + id + " "; 
	}
}

class WaitPerson2 implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private final Restaurant2 restaurant;
	BlockingQueue<Plate> filledOrders = new LinkedBlockingQueue<>();
	
	public WaitPerson2(Restaurant2 restaurant) {
		this.restaurant = restaurant;
	}
	
	public void placeOrder(Customer2 customer, Food food) {
		try {
			restaurant.orders.put(new Order(customer, this, food));
		} catch (InterruptedException e) {
			System.out.println(this + " placeOrder interrupted");
		}
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				//Блокируется пока блюдо не будет готово
				Plate plate = filledOrders.take();
				System.out.println(this + " received " + plate + " delivering to " + plate.getOrder().getCustomer());
				plate.getOrder().getCustomer().deliver(plate);
			}
		} catch (InterruptedException e) {
			System.out.println(this + " interrupted");
		}
		System.out.println(this + " off duty");
	}
	
	@Override
	public String toString() {
		return "WaitPerson " + id + " ";
	}
}

class Chef2 implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private final Restaurant2 restaurant;
	private static Random random = new Random(47);
	
	public Chef2(Restaurant2 restaurant) {
		this.restaurant = restaurant;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				//Блокируется до появления заказа
				Order order = restaurant.orders.take();
				Food food = order.item();
				TimeUnit.MILLISECONDS.sleep(random.nextInt(500));//Время на подготовку заказа
				Plate plate = new Plate(order, food);
				order.getWaitPerson().filledOrders.put(plate);
			}
		} catch (InterruptedException e) {
			System.out.println(this + " interrupted");
		}
		System.out.println(this + " off duty");
	}
	
	@Override
	public String toString() {
		return "Chef " + id + " ";
	}
}

class Restaurant2 implements Runnable {
	private List<WaitPerson2> waitPersons = new ArrayList<>();
	private List<Chef2> chefs = new ArrayList<>();
	private ExecutorService exec;
	private static Random random = new Random(47);
	BlockingQueue<Order> orders = new LinkedBlockingQueue<>();
	
	public Restaurant2(ExecutorService e, int nWaitPersons, int nChefs) {
		exec = e;
		for(int i = 0; i < nWaitPersons; i++) {
			WaitPerson2 waitPerson = new WaitPerson2(this);
			waitPersons.add(waitPerson);
			exec.execute(waitPerson);
		}
		for(int i = 0; i < nChefs; i++) {
			Chef2 chef = new Chef2(this);
			chefs.add(chef);
			exec.execute(chef);
		}
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				//Приходит посетитель, ему назначается официант
				WaitPerson2 waitPerson = waitPersons.get(random.nextInt(waitPersons.size()));
				Customer2 customer = new Customer2(waitPerson);
				exec.execute(customer);
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			System.out.println("Restaurant interrupted");
		}
		System.out.println("Restaurant closing");
	}
	
}

public class RestaurantWithQueues {

	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		Restaurant2 restaurant = new Restaurant2(exec, 5, 2);
		exec.execute(restaurant);
		if(args.length > 0)
			TimeUnit.SECONDS.sleep(new Integer(args[0]));
		else {
			System.out.println("Press 'Enter' to quit");
			System.in.read();
		}
		exec.shutdownNow();
	}

}

package com.brysekkel.concurrency;

import java.awt.peer.RobotPeer;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

class Car3 {
	private final int id;
	private boolean engine = false, driveTrain = false, wheels = false;
	
	public Car3(int id) {
		this.id = id;
	}
	
	public Car3() {
		this(-1);
	}
	
	public synchronized int getId() {
		return id;
	}
	
	public synchronized void addEngine() {
		engine = true;
	}
	
	public synchronized void addDriveTrain() {
		driveTrain = true;
	}
	
	public synchronized void addWheels() {
		wheels = true;
	}
	
	@Override
	public synchronized String toString() {
		return "Car " + id + " [ engine: " + engine +
				" driveTrain: " + driveTrain +
				" wheels: " + wheels + " ]"; 
	}
}

class CarQueue extends LinkedBlockingQueue<Car3> {}

class ChassisBuilder implements Runnable {
	private CarQueue carQueue;
	private int counter = 0;
	
	public ChassisBuilder(CarQueue carQueue) {
		this.carQueue = carQueue;
	}
	
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(500);
				//Создание рамы
				Car3 c = new Car3(counter++);
				System.out.println("ChassisBuilder created " + c);
				//Помещение в очередь
				carQueue.add(c);
			}
		} catch (InterruptedException e) {
			System.out.println("Interrupted: ChassisBuilder");
		}
		System.out.println("ChassisBuilder off");
	}
}

class Assembler implements Runnable {
	private CarQueue chassisQueue, finishingQueue;
	private Car3 car;
	private CyclicBarrier barrier = new CyclicBarrier(4);
	private RobotPool robotPool;
	
	public Assembler(CarQueue cq, CarQueue fq, RobotPool rp) {
		chassisQueue = cq;
		finishingQueue = fq;
		robotPool = rp;
	}
	
	public Car3 car() {
		return car;
	}
	
	public CyclicBarrier barrier() {
		return barrier;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				//Блокируется, пока рама не будет доступна
				car = chassisQueue.take();
				//Привлечение роботов для выполнения работы
				robotPool.hire(EngineRobot.class, this);
				robotPool.hire(DriveTrainRobot.class, this);
				robotPool.hire(WheelRobot.class, this);
				barrier.await(); // Пока роботы не закончат работу
				//Машина помещается в очередь finishingQueue для дальнейшей работы
				finishingQueue.add(car);
			}
		} catch (InterruptedException e) {
			System.out.println("Exiting Assembler via interrupt");
		} catch (BrokenBarrierException e) {
			throw new RuntimeException(e);
		}
		System.out.println("Assembler off");
	}
}

class Reporter implements Runnable {
	
	private CarQueue carQueue;
	
	public Reporter(CarQueue carQueue) {
		this.carQueue = carQueue;
	}
	
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println(carQueue.take());;
			}
		} catch (InterruptedException e) {
			System.out.println("Exiting Reporter via interrupt");
		}
		System.out.println("Reporter off");
	}
}

abstract class Robot implements Runnable {
	private RobotPool pool;
	private boolean engage = false;
	protected Assembler assembler;
		
	
	public Robot(RobotPool pool) {
		this.pool = pool;
	}
	
	public Robot assignAssembler(Assembler assembler) {
		this.assembler = assembler;
		return this;
	}
	
	public synchronized void engage() {
		engage = true;
		notifyAll();
	}
	
	abstract protected void performService();
	
	@Override
	public void run() {
		try {
			powerDown();
			while(!Thread.interrupted()) {
				performService();
				assembler.barrier().await();
				powerDown();
			}
		} catch (InterruptedException e) {
			System.out.println("Exiting " + this + " via interrupt");
		} catch (BrokenBarrierException e) {
			throw new RuntimeException(e);
		}
		System.out.println(this + " off");
	}
	
	private synchronized void powerDown() throws InterruptedException {
		engage = false;
		assembler = null;
		pool.release(this);
		while (!engage) {
			wait();
		}
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
}

class EngineRobot extends Robot {
	
	public EngineRobot(RobotPool pool) {
		super(pool);
	}
	
	@Override
	protected void performService() {
		System.out.println(this + " installing engine");
		assembler.car().addEngine();
	}
}

class DriveTrainRobot extends Robot {
	
	public DriveTrainRobot(RobotPool pool) {
		super(pool);
	}
	
	@Override
	protected void performService() {
		System.out.println(this + " installing DriveTrain");
		assembler.car().addDriveTrain();
	}
}

class WheelRobot extends Robot {
	
	public WheelRobot(RobotPool pool) {
		super(pool);
	}
	
	@Override
	protected void performService() {
		System.out.println(this + " installing WhelsTrain");
		assembler.car().addWheels();
	}
}

class RobotPool {
	private Set<Robot> pool = new HashSet<>();
	
	public synchronized void add(Robot r) {
		pool.add(r);
		notifyAll();
	}
	
	public synchronized void hire(Class<? extends Robot> robotType, Assembler d) throws InterruptedException {
		for(Robot robot : pool) {
			if(robot.getClass().equals(robotType)) {
				pool.remove(robot);
				robot.assignAssembler(d);
				robot.engage();			//Включение для выполнения задания
				return;
			}
		}
		wait();							//Нет доступных кандидатов
		hire(robotType, d);				//Повторная попытка с рекурсией
	}
	
	public synchronized void release(Robot robot) {
		add(robot);
	}
}

public class CarBuilder {

	public static void main(String[] args) throws Exception{
		CarQueue chassisQueue = new CarQueue(),
				finishingQueue = new CarQueue();
		ExecutorService exec = Executors.newCachedThreadPool();
		RobotPool pool = new RobotPool();
		exec.execute(new EngineRobot(pool));
		exec.execute(new DriveTrainRobot(pool));
		exec.execute(new WheelRobot(pool));
		exec.execute(new Assembler(chassisQueue, finishingQueue, pool));
		exec.execute(new Reporter(finishingQueue));
		//Создание рам -> приводим конвейер в движение
		exec.execute(new ChassisBuilder(chassisQueue));
		TimeUnit.SECONDS.sleep(7);
		exec.shutdownNow();
		

	}

}

package com.brysekkel.gui.swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;

class Task implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	
	@Override
	public void run() {
		System.out.println(this + " started");
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException ex) {
			System.out.println(this + " interrupted");
			return;
		}
		System.out.println(this + " completed");
	}
	
	@Override
	public String toString() {
		return "Task " + id;
	}
	
	public int id() {
		return id;
	}
}

public class InterruptableLongRunningTask extends JFrame {
	private JButton 
		b1 = new JButton("Start Long Running Task"),
		b2 = new JButton("End Long Running Task");
	ExecutorService exec = Executors.newSingleThreadExecutor();
	
	
	public InterruptableLongRunningTask() {
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Task task = new Task();
				exec.execute(task);
				System.out.println(task + " added to the queue");
			}
		});
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				exec.shutdownNow();
			}
		});
		setLayout(new FlowLayout());
		add(b1);
		add(b2);
	}	
	
	public static void main(String[] args) {
		SwingConsole.run(new InterruptableLongRunningTask(), 200, 150);
	}

}

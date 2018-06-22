package com.brysekkel.concurrency;

class UnresponsiveUI {
	private volatile double d = 1;
	
	public UnresponsiveUI() throws Exception {
		while (d > 0)
			d = d + (Math.PI + Math.E) / d;
		System.in.read(); //никогда не будет здесь!
	}
}

public class ResponsiveUI extends Thread {
	
	private static volatile double d = 1;
	
	public ResponsiveUI() {
		setDaemon(true);
		start();
	}
	
	@Override
	public void run() {
		while (d > 0)
			d = d + (Math.PI + Math.E) / d;
	}
	
	public static void main(String[] args) throws Exception {
		//new UnresponsiveUI(); //процесс придется уничтожить!
		new ResponsiveUI();
		System.in.read();
		System.out.println(d);

	}

}

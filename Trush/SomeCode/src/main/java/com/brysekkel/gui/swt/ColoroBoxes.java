package com.brysekkel.gui.swt;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import com.brysekkel.concurrency.DaemonThreadPoolExecutor;

class CBox extends Canvas implements Runnable {
	
	private static Random random = new Random();
	private int pause;
	private RGB cColor = newColor();

	class CBoxPaintListener implements PaintListener {
		@Override
		public void paintControl(PaintEvent e) {
			Color color = new Color(e.display, cColor);
			e.gc.setBackground(color);
			Point size = getSize();
			e.gc.fillRectangle(0, 0, size.x, size.y);
			color.dispose();
		}
	}
	
	private static RGB newColor() {
		return new RGB(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}
	
	public CBox(Composite parent, int pause) {
		super(parent, SWT.NONE);
		this.pause = pause;
		addPaintListener(new CBoxPaintListener());
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				cColor = newColor();
				getDisplay().asyncExec(new Runnable() {
					
					@Override
					public void run() {
						try {
							redraw();
						} catch (SWTException e) {
							// Допустимо при завершении родителя
						}
						
					}
				});
				TimeUnit.MILLISECONDS.sleep(pause);
			}
		} catch (InterruptedException | SWTException e) {
			// Допустимый способ выхода
		}
	}
}

public class ColoroBoxes implements SWTApplication {

	private int grid = 12;
	private int pause = 50;
	
	@Override
	public void createContents(Composite parent) {
		GridLayout gridLayout = new GridLayout(grid, true);
		gridLayout.horizontalSpacing = 0;
		gridLayout.verticalSpacing = 0;
		parent.setLayout(gridLayout);
		ExecutorService exec = new DaemonThreadPoolExecutor();
		for(int i = 0; i < (grid * grid); i++) {
			final CBox cb = new CBox(parent, pause);
			cb.setLayoutData(new GridData(GridData.FILL_BOTH));
			exec.execute(cb);
		}

	}

	public static void main(String[] args) {
		ColoroBoxes boxes = new ColoroBoxes();
		if(args.length > 0)
			boxes.grid = new Integer(args[0]);
		if(args.length > 1)
			boxes.pause = new Integer(args[1]);
		SWTConsole.run(new ColoroBoxes(), 500, 400);
	}

}

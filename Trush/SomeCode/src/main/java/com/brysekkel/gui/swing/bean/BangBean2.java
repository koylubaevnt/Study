package com.brysekkel.gui.swing.bean;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.brysekkel.gui.swing.SwingConsole;

public class BangBean2 extends JPanel implements Serializable {

	private int xm, ym;
	private int cSize = 20;
	private String text = "Bang!";
	private int fontSize = 48;
	private Color color = Color.RED;
	private List<ActionListener> actionListeners = new ArrayList<>();
	
	public BangBean2() {
		addMouseListener(new ML());
		addMouseMotionListener(new MML());
	}
	
	public synchronized int getCircleSize() {
		return cSize;
	}
	
	public synchronized void setCircleSize(int cSize) {
		this.cSize = cSize;
	}
	
	public synchronized String getBangText() {
		return text;
	}
	
	public synchronized void setBangText(String text) {
		this.text = text;
	}
	
	public synchronized int getFontSize() {
		return fontSize;
	}
	
	public synchronized void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	
	public synchronized Color getTextColor() {
		return color;
	}
	
	public synchronized void setTextColor(Color color) {
		this.color = color;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawOval(xm - cSize / 2, ym - cSize / 2, cSize, cSize);
	}
	
	public synchronized void addActionListener(ActionListener l) {
		actionListeners.add(l);
	}
	
	public synchronized void removeActionListener(ActionListener l) {
		actionListeners.remove(l);
	}
	
	public void notifyListeners() {
		ActionEvent a = new ActionEvent(BangBean2.this, ActionEvent.ACTION_PERFORMED, null);
		List<ActionListener> lv = null;
		
		//Создаем копию ArrayList на тот случай, если слушатель будет добавлен в момент вызова
		synchronized (this) {
			lv = new ArrayList<>(actionListeners);
		}
		
		for(ActionListener al : lv)
			al.actionPerformed(a);
	}
	
	class ML extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			Graphics g = getGraphics();
			g.setColor(color);
			g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
			int width = g.getFontMetrics().stringWidth(text);
			g.drawString(text, (getSize().width - width) / 2, getSize().height / 2);
			g.dispose();
			notifyListeners();
		}
	}
	
	class MML extends MouseMotionAdapter {
		@Override
		public void mouseMoved(MouseEvent e) {
			xm = e.getX();
			ym = e.getY();
			repaint();
		}
	}
	
	public static void main(String[] args) {
		BangBean2 bb2 = new BangBean2();
		bb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("ActionEvent " + e);				
			}
		});
		bb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("BangBean2 action");
			}
		});
		bb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("More action");
				
			}
		});
		
		JFrame frame = new JFrame();
		frame.add(bb2);
		SwingConsole.run(frame, 300, 300);
	}
	
	
}

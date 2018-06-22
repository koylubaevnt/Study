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
import java.util.TooManyListenersException;

import javax.swing.JPanel;

public class BangBean extends JPanel implements Serializable {

	private int xm, ym;
	private int cSize = 20;
	private String text = "Bang!";
	private int fontSize = 48;
	private Color color = Color.RED;
	private ActionListener actionListener;
	
	public BangBean() {
		addMouseListener(new ML());
		addMouseMotionListener(new MML());
	}
	
	public int getCircleSize() {
		return cSize;
	}
	
	public void setCircleSize(int cSize) {
		this.cSize = cSize;
	}
	
	public String getBangText() {
		return text;
	}
	
	public void setBangText(String text) {
		this.text = text;
	}
	
	public int getFontSize() {
		return fontSize;
	}
	
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	
	public Color getTextColor() {
		return color;
	}
	
	public void setTextColor(Color color) {
		this.color = color;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawOval(xm - cSize / 2, ym - cSize / 2, cSize, cSize);
	}
	
	public void addActionListener(ActionListener l) throws TooManyListenersException {
		if(actionListener != null)
			throw new TooManyListenersException();
		actionListener = l;
	}
	
	public void removeActionListener(ActionListener l) {
		actionListener = null;
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
			if(actionListener != null)
				actionListener.actionPerformed(new ActionEvent(BangBean.this, ActionEvent.ACTION_PERFORMED, null));
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
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}
	
}

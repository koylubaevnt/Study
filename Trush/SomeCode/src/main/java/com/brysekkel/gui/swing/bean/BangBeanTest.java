package com.brysekkel.gui.swing.bean;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TooManyListenersException;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.brysekkel.gui.swing.SwingConsole;

public class BangBeanTest extends JFrame {

	private JTextField txt = new JTextField(20);
	
	class BBL implements ActionListener {
		private int count = 0;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			txt.setText("BangBean action " + count++);
		}
	}
	
	public BangBeanTest() {
		BangBean bb = new BangBean();
		try {
			bb.addActionListener(new BBL());
		} catch (TooManyListenersException e) {
			txt.setText("Too many liateners");
		}
		add(bb);
		add(BorderLayout.SOUTH, txt);
	}
	
	public static void main(String[] args) {
		SwingConsole.run(new BangBeanTest(), 400, 500);
	}

}

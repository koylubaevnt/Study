package com.brysekkel.gui.swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

public class Popup extends JFrame {

	private JPopupMenu popup = new JPopupMenu();
	private JTextField t = new JTextField(10);
	
	public Popup() {
		setLayout(new FlowLayout());
		add(t);
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				t.setText(((JMenuItem)e.getSource()).getText());
			}
		};
		JMenuItem m = new JMenuItem("Hinter");
		m.addActionListener(al);
		popup.add(m);
		m = new JMenuItem("Yon");
		m.addActionListener(al);
		popup.add(m);
		m = new JMenuItem("Afar");
		m.addActionListener(al);
		popup.add(m);
		popup.addSeparator();
		popup.add(m);
		m = new JMenuItem("Stay Here");
		m.addActionListener(al);
		popup.add(m);
		PopupListener pl = new PopupListener();
		addMouseListener(pl);
		t.addMouseListener(pl);
	}
	
	class PopupListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			maybeShowPopup(e);
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			maybeShowPopup(e);
		}
		
		private void maybeShowPopup(MouseEvent e) {
			if(e.isPopupTrigger())
				popup.show(e.getComponent(), e.getX(), e.getY());
		}
	}
	
	public static void main(String[] args) {
		SwingConsole.run(new Popup(), 300, 200);
	}

}

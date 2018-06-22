package com.brysekkel.gui.swing;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class List extends JFrame {

	private String[] flavors = {
			"Chocolate", "Strawberry", "Vanilla Fudge Swirl",
			"Mint Chip", "Mocha Almond Fudge", "Rum Raisin",
			"Praline Cream", "Mud Pie"
	};
	private DefaultListModel lItems = new DefaultListModel<>();
	private JList list = new JList<>(lItems);
	private JTextArea t = new JTextArea(flavors.length, 20);
	private JButton b = new JButton("Add Item");
	private int count = 0;
	private ActionListener bl = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(count < flavors.length) {
				lItems.add(0, flavors[count++]);
			} else {
				b.setEnabled(false);
			}
		}
	};
	private ListSelectionListener ll = new ListSelectionListener() {
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting()) return;
			t.setText("");
			for(Object item : list.getSelectedValues()) {
				t.append(item + "\n");
			}
		}
	};  
	
	public List() {
		t.setEditable(false);
		setLayout(new FlowLayout());
		Border border = BorderFactory.createMatteBorder(1, 1, 2, 2, Color.BLACK);
		list.setBorder(border);
		t.setBorder(border);
		for(int i = 0; i < 4; i++)
			lItems.addElement(flavors[count++]);
		add(t);
		add(list);
		add(b);
		
		list.addListSelectionListener(ll);
		b.addActionListener(bl);
	}
	
	public static void main(String[] args) {
		SwingConsole.run(new List(), 250, 375);
	}

}

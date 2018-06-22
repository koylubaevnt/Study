package com.brysekkel.gui.swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.brysekkel.typeinfo.containers.Countries;

public class TextArea extends JFrame {

	private JButton 
		b = new JButton("Add Data"),
		c = new JButton("Clear Data");
	private JTextArea t = new JTextArea(20, 40);
	private Map<String, String> m = new HashMap<>();
	
	public TextArea() {
		m.putAll(Countries.capitals());
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(Map.Entry entry : m.entrySet()) {
					t.append(entry.getKey() + ": " + entry.getValue() + "\n");
				}
				
			}
		});
		c.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				t.setText("");
			}
		});
		
		setLayout(new FlowLayout());
		add(new JScrollPane(t));
		add(b);
		add(c);
	}
	
	public static void main(String[] args) {
		SwingConsole.run(new TextArea(), 475, 425);

	}

}

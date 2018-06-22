package com.brysekkel.gui.swing;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class LookAndFeel extends JFrame {

	private String[] choices = 
			"Eeny Meeny Minnie Mickey Moe Larry Curly".split(" ");
	
	private Component[] samples = {
			new JButton("JButton"),
			new JTextField("JTextField"),
			new JLabel("JLabel"),
			new JCheckBox("JCheckBox"),
			new JRadioButton("Radio"),
			new JComboBox<>(choices),
			new JList<>(choices)
	};
	
	public LookAndFeel() {
		super("Look And Feel");
		setLayout(new FlowLayout());
		for(Component component : samples)
			add(component);
	}
	
	private static void usageError() {
		System.out.println("Usage: LookAndFeel [cross|system|motif]");
		System.exit(1);
	}
	
	public static void main(String[] args) {
		if(args.length == 0) usageError();
		String laf = null;
		if(args[0].equals("cross")) {
			laf = UIManager.getCrossPlatformLookAndFeelClassName();
		} else if(args[0].equals("system")) {
			laf = UIManager.getSystemLookAndFeelClassName();
		} else if(args[0].equals("motif")) {
			laf = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
		} else 
			usageError();
		try {
			UIManager.setLookAndFeel(laf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		SwingConsole.run(new LookAndFeel(), 300, 300);
	}

}

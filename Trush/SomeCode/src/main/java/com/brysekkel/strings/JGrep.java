//: strings/JGrep.java
package com.brysekkel.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.brysekkel.utils.notmine.TextFile;

public class JGrep {
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Usage: java JGrep file regex");
			System.exit(0);
		}
		//System.out.println(args[0] + " - " + args[1]);
		Pattern p = Pattern.compile(args[1]);
		//Pattern p = Pattern.compile("\\b[Ssct]\\w+");
		int i = 0;
		Matcher m = p.matcher("");
		for(String line : new TextFile(args[0])) {
			m.reset(line);
			while(m.find()) {
				System.out.println(i++ + ": " + m.group() + ": " + m.start());
			}
		}
	}

}

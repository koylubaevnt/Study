package com.brysekkel.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegularExpression {

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Usage:\njava TestRegularExpression " +
					"characterSequence regularExpression+");
			System.exit(0);
		}
		System.out.println("Input: \"" + args[0] + "\"");
		for(String arg : args) {
			System.out.println("Regular expression: \"" + arg + "\"");
			Pattern pattern = Pattern.compile(arg);
			Matcher matcher = pattern.matcher(args[0]);
			while (matcher.find()) {
				System.out.println("Match \"" + matcher.group() + "\" at postions " +
						matcher.start() + "-" + (matcher.end() - 1));
			}
		}

	}

}

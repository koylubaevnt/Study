package com.brysekkel.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.brysekkel.utils.notmine.TextFile;

/*! Here's a block of text to use as input to
    the regular expression mathcer. Note that we'll
    first extract the block of text by looking for
    the special delimiters, then process the
    extracted block. !*/

public class Replacements {

	public static void main(String[] args) {
		String s = TextFile.read("src\\main\\java\\com\\brysekkel\\strings\\Replacements.java");
		Matcher m = 
				Pattern.compile("/\\*!(.*)!\\*/", Pattern.DOTALL)
				.matcher(s);
		if(m.find()) {
			s = m.group(1); //совпадение подвыражения в круглых скобках regex
		}
		s = s.replaceAll(" {2,}", " "); //Два и более пробела заменяются одним пробелом 
		s = s.replaceAll("(?m)^ +", ""); //один и более пробелов в начале строки заменяются пустой строкой. Для выполнения должен быть включен режим MULTILINE
		System.out.println(s);
		
		s = s.replaceFirst("[aeiou]", "(VOWEL1)");
		StringBuffer sb = new StringBuffer();
		m  = Pattern.compile("[aeiou]")
				.matcher(s);
		while(m.find()) {
			m.appendReplacement(sb, m.group().toUpperCase());
		}
		m.appendTail(sb);
		System.out.println(sb);
	}

}

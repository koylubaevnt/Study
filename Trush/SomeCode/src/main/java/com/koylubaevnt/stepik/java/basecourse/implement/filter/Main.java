package com.koylubaevnt.stepik.java.basecourse.implement.filter;

public class Main {

	public static void main(String[] args) {
		String[] spam = {"SPAM", "test", "hello"};
		
		TextAnalyzer[] textAnalyzers = {new TooLongTextAnalyzer(100), new NegativeTextAnalyzer(), new SpamAnalyzer(spam)};
		Main main = new Main();
		System.out.println(main.checkLabels(textAnalyzers, "hi my string!"));
		System.out.println(main.checkLabels(textAnalyzers, "test SPAM message"));
		System.out.println(main.checkLabels(textAnalyzers, "qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop"));
		System.out.println(main.checkLabels(textAnalyzers, "qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop"));
		System.out.println(main.checkLabels(textAnalyzers, "qwetestqwe"));
		
	}

	public Label checkLabels(TextAnalyzer[] analyzers, String text) {
	    Label label;
		for(int i = 0; i < analyzers.length; i++) {
			label = analyzers[i].processText(text);
			if(label != Label.OK) {
				return label;
			};
	    }
		return Label.OK;
	}
}

package com.koylubaevnt.stepik.java.basecourse.implement.filter;

public class NegativeTextAnalyzer extends KeywordAnalyzer {

	private final String[] keywords;
	
	public NegativeTextAnalyzer() {
		keywords = new String[] { ":(", "=(", ":|" };
	}

	@Override
	protected String[] getKeywords() {
		return keywords;
	}

	@Override
	protected Label getLabel() {
		return Label.NEGATIVE_TEXT;
	}
	
	

}

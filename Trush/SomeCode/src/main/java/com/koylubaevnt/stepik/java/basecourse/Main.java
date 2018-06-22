package com.koylubaevnt.stepik.java.basecourse;

public class Main {

	public static void main(String[] args) {
		//Main main = new Main();
		//main.task2_4_10();
		
	}
	
	private void task2_4_10() {
		String[] roles = {"Городничий","Аммос Федорович", "Артемий Филиппович", "Лука Лукич"};
		String[] textLines = {
				"Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.",
				"Аммос Федорович: Как ревизор?",
				"Артемий Филиппович: Как ревизор?",
				"Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.",
				"Аммос Федорович: Вот те на!",
				"Артемий Филиппович: Вот не было заботы, так подай!",
				"Лука Лукич: Господи боже! еще и с секретным предписаньем!"};
		System.out.println(printTextPerRole(roles, textLines));
	}
	
	private String printTextPerRole(String[] roles, String[] textLines) {
	    StringBuilder sb = new StringBuilder();
	    for(int i = 0; i < roles.length; i++) {
	    	String role = roles[i] + ":";
	    	sb.append(role).append(System.lineSeparator());
	        for(int j = 0; j < textLines.length; j++) {
	            if(textLines[j].startsWith(role)) {
	                sb.append(j + 1).append(")").append(textLines[j].replaceFirst(role, "")).append(System.lineSeparator());
	            }
	        }
	        sb.append(System.lineSeparator());
	    }
	    return sb.toString();
	}
}

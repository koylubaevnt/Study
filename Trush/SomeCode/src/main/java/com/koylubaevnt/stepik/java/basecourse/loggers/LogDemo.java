package com.koylubaevnt.stepik.java.basecourse.loggers;

import java.util.Arrays;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

public class LogDemo {

	private static final Logger LOGGER = Logger.getLogger(LogDemo.class.getName());
	
	public static void main(String[] args) {
		LOGGER.log(Level.FINE, "Started with arguments: {0}", Arrays.toString(args));
		try{
			randomlyFailingAlgorithms();
		} catch (IllegalStateException e) {
			LOGGER.log(Level.SEVERE, "Exception caught", e);
			System.exit(2);
		}
		LOGGER.fine("Finished successfully");
	}

	private static void randomlyFailingAlgorithms() {
		double randomNumber = Math.random();
		LOGGER.log(Level.FINE, "Generated random number: {0}", randomNumber);
		if(randomNumber < 0.5) {
			throw new IllegalStateException("Invalid phase of Moon");
		}
	}

	private static void configureLogging() {
	    /*Логгер с именем "org.stepic.java.logging.ClassA" принимал сообщения всех уровней.
	    Логгер с именем "org.stepic.java.logging.ClassB" принимал только сообщения уровня WARNING и серьезнее.
	    Все сообщения, пришедшие от нижестоящих логгеров на уровень "org.stepic.java", 
	    независимо от серьезности сообщения печатались в консоль в формате XML (*) 
	    и не передавались вышестоящим обработчикам на уровнях "org.stepic", "org" и "".*/
	    final Logger loggerClassA = Logger.getLogger("org.stepic.java.logging.ClassA"); 
	    final Logger loggerClassB = Logger.getLogger("org.stepic.java.logging.ClassB");
	    final Logger loggerAll = Logger.getLogger("org.stepic.java");    
	    
	    Formatter formatter = new XMLFormatter();
	    ConsoleHandler consoleHandler = new ConsoleHandler();
	    consoleHandler.setLevel(Level.ALL);
	    consoleHandler.setFormatter(formatter);
	    
	    loggerClassA.setLevel(Level.ALL);
	    loggerClassB.setLevel(Level.WARNING);
	    loggerAll.setLevel(Level.ALL);
	    loggerAll.addHandler(consoleHandler);
	    loggerAll.setUseParentHandlers(false);
	}
}

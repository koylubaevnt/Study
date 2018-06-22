package com.koylubaevnt.stepik.java.basecourse.throwables;

public class Test {

	public static void main(String[] args) {
        System.out.println(getCallerClassAndMethodName());
        anotherMethod();
    }

    private static void anotherMethod() {
        System.out.println(getCallerClassAndMethodName());
    }

	public static double sqrt(double x) {
	    if(x < 0) {
	        throw new IllegalArgumentException("Expected non-negative number, got " + x);
	    }
	    return Math.sqrt(x); // your implementation here
	}
	
	public static String getCallerClassAndMethodName() {
	    Throwable t = new Throwable();
	    StackTraceElement[] stackTrace = t.getStackTrace();
	    if(stackTrace.length < 3) {
	    	return null;
	    } else  {
	    	return stackTrace[2].getClassName() +"#"+ stackTrace[2].getMethodName();
	    }
	}
}


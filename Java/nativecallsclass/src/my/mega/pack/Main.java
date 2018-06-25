package my.mega.pack;

public class Main {

	public static void main(String[] args) {
		
		System.out.println(System.getProperty("java.library.path"));
		
		NativeCallsClass nativeCallsClass = new NativeCallsClass();
		System.out.println("Java.Main => calling nativeCallsClass.printString(\"Hello world\")");
		int retVal = nativeCallsClass.printString("Hello world");
		System.out.println("Java.Main => retVal=" + retVal);
		
		System.out.println("Java.Main => calling nativeCallsClass.printOne()");
		nativeCallsClass.printOne();
		
		System.out.println("Java.Main => calling nativeCallsClass.printTwo(3)");
		nativeCallsClass.printTwo(3);
	}

}

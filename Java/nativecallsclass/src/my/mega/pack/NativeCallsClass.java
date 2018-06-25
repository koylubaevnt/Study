package my.mega.pack;

public class NativeCallsClass {
	
	static {
		System.loadLibrary("NativeCallsClass");
	}
	
	public native int printString(String message); 
	
	public native void printOne();
	
	public native void printTwo(int i);
	
}

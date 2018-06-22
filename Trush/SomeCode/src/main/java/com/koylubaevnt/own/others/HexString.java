package com.koylubaevnt.own.others;

public class HexString {
	
	private static final int HI_BYTE_MASK = 0xf0;
	private static final int LOW_BYTE_MASK = 0x0f;
	private static final char[] HEX_SYMBOLS = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	
	public static boolean isEmpty(final CharSequence sequence) {
		return sequence == null;
	}
	
	public static String toHexString(final byte[] data) {
		if(data == null) {
			throw new IllegalArgumentException("Must be non-null");
		}
		final StringBuffer buffer = new StringBuffer(2 * data.length);
		for(byte item : data) {
			buffer.append(HEX_SYMBOLS[(HI_BYTE_MASK & item) >>> 4]);
			buffer.append(HEX_SYMBOLS[(LOW_BYTE_MASK & item)]);
		}
		return buffer.toString();
	}
	
	public static void main(String[] args) {
		String result = toHexString(new byte[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20});
		System.out.println(result);
	}
}

package com.koylubaevnt.stepik.java.basecourse.implement;

import java.util.Arrays;

public final class AsciiCharSequence implements CharSequence {

	private byte[] array;
	
	public AsciiCharSequence(byte[] array) {
		this.array = array;
	}
	
	public AsciiCharSequence(byte[] array, int offset, int count) {
        if (offset > array.length - count) {
            throw new StringIndexOutOfBoundsException(offset + count);
        }
        this.array = Arrays.copyOfRange(array, offset, offset+count);
    }
	
	@Override
	public int length() {
		return array.length;
	}

	@Override
	public char charAt(int index) {
		return (char) array[index];
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return ((start == 0) && (end == array.length)) ? this
                : new AsciiCharSequence(array, start, end - start); 
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
        for(int i = 0; i < array.length; i++) {
            sb.append((char)array[i]);
        }
		return sb.toString();
	}
}

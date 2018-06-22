package com.brysekkel.typeinfo.arrays;

import java.util.Random;

import com.brysekkel.typeinfo.arrays.CountingGenerator.Character;
import com.brysekkel.typeinfo.generics.Generator;

public class RandomGenerator {

	private static Random random = new Random(47);
	
	public static class Boolean implements Generator<java.lang.Boolean> {
		@Override
		public java.lang.Boolean next() {
			return random.nextBoolean();
		}
		
	}
	
	public static class Byte implements Generator<java.lang.Byte> {
		@Override
		public java.lang.Byte next() {
			return (byte)random.nextInt();
		}
		
	}
	
	public static class Character implements Generator<java.lang.Character> {
		@Override
		public java.lang.Character next() {
			return CountingGenerator.chars[
			    random.nextInt(CountingGenerator.chars.length)];
		}
		
	}
	
	public static class String extends CountingGenerator.String {

		{ cg = new Character(); }

		public String() {}
		
		public String(int length) {
			super(length);
		}
		
	}
	
	public static class Short implements Generator<java.lang.Short> {
		@Override
		public java.lang.Short next() {
			return (short)random.nextInt();
		}
		
	}
	
	public static class Integer implements Generator<java.lang.Integer> {
		private int mod = 10000;

		public Integer() { }
		
		public Integer(int mod) {
			this.mod = mod;
		}
		
		@Override
		public java.lang.Integer next() {
			return random.nextInt(mod);
		}
	}
	
	public static class Long implements Generator<java.lang.Long> {
		private int mod = 10000;

		public Long() { }
		
		public Long(int mod) {
			this.mod = mod;
		}
		
		@Override
		public java.lang.Long next() {
			return new java.lang.Long(random.nextInt(mod));
		}
	}
	
	public static class Float implements Generator<java.lang.Float> {
				@Override
		public java.lang.Float next() {
			//Усечение дробной части до 2 цифр
			int trimmed = Math.round(random.nextFloat() * 100);
			return ((float) trimmed) / 100;
		}
		
	}
	
	public static class Double implements Generator<java.lang.Double> {
		@Override
		public java.lang.Double next() {
			//Усечение дробной части до 2 цифр
			long trimmed = Math.round(random.nextDouble() * 100);
			return ((double) trimmed) / 100;
		}
		
	}
	

}

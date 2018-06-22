package com.brysekkel.typeinfo.enumerated;

enum Search { HITHER, TON }

public class UpcastEnum {

	public static void main(String[] args) {
		Search[] vals = Search.values();
		Enum e = Search.HITHER;
		//e.values() // У Enum  нет метода values()
		for(Enum en : e.getClass().getEnumConstants())
			System.out.println(en);
	}
	
}

package com.brysekkel.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AsListInference {

	public static void main(String[] args) {
		List<Snow> snow1 = Arrays.asList(new Crusty(), new Slush(), new Powder());
		List<Snow> snow2 = Arrays.asList(new Light(), new Heavy());
		List<Snow> snow3 = new ArrayList<>();
		Collections.addAll(snow3, new Light(), new Heavy());
		//ListIterator<E>
		//LinkedList<E>
		for(Snow s : snow1) {
			System.out.println(s);
		}
		
		for(Snow s : snow2) {
			System.out.println(s);
		}
		
		for(Snow s : snow3) {
			System.out.println(s);
		}
		
		System.out.println(snow1);
	}

}

class Snow {}
class Powder extends Snow {}
class Light extends Powder {}
class Heavy extends Powder {}
class Crusty extends Snow {}
class Slush extends Snow {}

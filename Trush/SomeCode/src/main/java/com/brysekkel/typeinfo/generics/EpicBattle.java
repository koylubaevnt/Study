package com.brysekkel.typeinfo.generics;

import java.util.List;

interface SuperPower {}
interface XRayVision extends SuperPower {
	void seeThroughWalls();
}
interface SuperHearing extends SuperPower {
	void hearSubtleNoises();
}
interface SuperSmell extends SuperPower {
	void trackBySmell();
}

class SuperHero<POWER extends SuperPower> {
	POWER power;
	public SuperHero(POWER power) {
		this.power = power;		
	}
	POWER getPower() {
		return power;
	}
}

class SuperSleuth<POWER extends XRayVision> extends SuperHero<POWER>{

	public SuperSleuth(POWER power) {
		super(power);
	}
	
	void see() {
		power.seeThroughWalls();
	}
}

class CanineHero<POWER extends SuperHearing & SuperSmell> extends SuperHero<POWER> {

	public CanineHero(POWER power) {
		super(power);
	}
	
	public void hear() {
		power.hearSubtleNoises();
	}
	
	public void smell() {
		power.trackBySmell();
	}
}

class SuperHearSmell implements SuperHearing, SuperSmell {

	@Override
	public void trackBySmell() { }

	@Override
	public void hearSubtleNoises() { }
	
}

class DogBoy extends CanineHero<SuperHearSmell> {

	public DogBoy() {
		super(new SuperHearSmell());
	}
	
}

public class EpicBattle {

	//ограничения в обобщенных методах
	static <POWER extends SuperHearing> void useSuperHearing(SuperHero<POWER> hero) {
		hero.getPower().hearSubtleNoises();
	}

	static <POWER extends SuperHearing & SuperSmell> void superFind(SuperHero<POWER> hero) {
		hero.getPower().hearSubtleNoises();
		hero.getPower().trackBySmell();
	}
	
	public static void main(String[] args) {
		DogBoy dogBoy = new DogBoy();
		useSuperHearing(dogBoy);
		superFind(dogBoy);
		// Так поступить можно:
		List<? extends SuperHearing> audioBoys;
		// А так нельзя:
		//List<? extends SuperHearing & SuperSmell> docBoys;
	}

}

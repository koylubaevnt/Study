package com.brysekkel.typeinfo.enumerated;

public enum RoShamBo2 implements Competitor<RoShamBo2>{
	PAPER(Outcome.DRAW, Outcome.LOSE, Outcome.WIN),
	SCISSORS(Outcome.WIN, Outcome.DRAW, Outcome.LOSE),
	ROCK(Outcome.LOSE, Outcome.WIN, Outcome.DRAW);
	private Outcome vPAPER, vSCISSORS, vROCK;
	
	private RoShamBo2(Outcome paper, Outcome scissors, Outcome rock) {
		this.vPAPER = paper;
		this.vROCK = rock;
		this.vSCISSORS = scissors;
	}
	
	@Override
	public Outcome complete(RoShamBo2 it) {
		switch (it) {
			default:
			case PAPER:
				return vPAPER;
			case ROCK:
				return vROCK;
			case SCISSORS:
				return vSCISSORS;
		}
	}
	
	public static void main(String[] args) {
		RoShamBo.play(RoShamBo2.class, 20);
	}
}

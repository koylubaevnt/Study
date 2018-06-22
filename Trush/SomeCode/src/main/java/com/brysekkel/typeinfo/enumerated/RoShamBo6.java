package com.brysekkel.typeinfo.enumerated;

enum RoShamBo6 implements Competitor<RoShamBo6>{
	PAPER, SCISSORS, ROCK;
	private static Outcome[][] table = {
			{ Outcome.DRAW, Outcome.LOSE, Outcome.WIN },//PAPER
			{ Outcome.WIN, Outcome.DRAW, Outcome.LOSE},//SCISSORS
			{ Outcome.LOSE, Outcome.WIN, Outcome.DRAW } //ROCK
	};
	@Override
	public Outcome complete(RoShamBo6 competitor) {
		return table[this.ordinal()][competitor.ordinal()];
	}
	public static void main(String[] args) {
		RoShamBo.play(RoShamBo6.class, 20);

	}

}

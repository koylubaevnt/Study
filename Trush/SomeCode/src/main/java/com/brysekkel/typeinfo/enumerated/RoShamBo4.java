package com.brysekkel.typeinfo.enumerated;

public enum RoShamBo4 implements Competitor<RoShamBo4> {
	PAPER {
		@Override
		public Outcome complete(RoShamBo4 it) {
			return complete(ROCK, it);
		}
	},
	SCISSORS {
		@Override
		public Outcome complete(RoShamBo4 it) {
			return complete(PAPER, it);
		}
	},
	ROCK {
		@Override
		public Outcome complete(RoShamBo4 it) {
			return complete(SCISSORS, it);
		}
	};
	
	Outcome complete(RoShamBo4 loser, RoShamBo4 opponent) {
		return ((opponent == this) 		? Outcome.DRAW
				: ((opponent == loser) 	? Outcome.WIN
										: Outcome.LOSE));
	}
	
	public static void main(String[] args) {
		RoShamBo.play(RoShamBo4.class, 20);
	}
}

package com.brysekkel.typeinfo.enumerated;

public enum RoShamBo3 implements Competitor<RoShamBo3> {
	PAPER {
		@Override
		public Outcome complete(RoShamBo3 it) {
			switch (it) {
				default:
				case PAPER: return Outcome.DRAW;
				case SCISSORS: return Outcome.LOSE;
				case ROCK: return Outcome.WIN;
			}
		}
	},
	SCISSORS {
		@Override
		public Outcome complete(RoShamBo3 it) {
			switch (it) {
				default:
				case PAPER: return Outcome.WIN;
				case SCISSORS: return Outcome.DRAW;
				case ROCK: return Outcome.LOSE;
			}
		}
	},
	ROCK {
		@Override
		public Outcome complete(RoShamBo3 it) {
			switch (it) {
				default:
				case PAPER: return Outcome.LOSE;
				case SCISSORS: return Outcome.WIN;
				case ROCK: return Outcome.DRAW;
			}
		}
	};
	
	@Override
	public abstract Outcome complete(RoShamBo3 it);
	
	public static void main(String[] args) {
		RoShamBo.play(RoShamBo3.class, 20);
	}
}

package com.brysekkel.typeinfo.enumerated;

public interface Competitor<T extends Competitor<T>> {
	Outcome complete(T competitor);
}

package com.koylubaevnt.stepik.java.basecourse.implement;

import java.time.LocalDate;

public interface Orders {
	
	static Order[] filterByDate(Order[] orders, LocalDate date) {
		return new Order[0];
	};
}

package com.apress.prospring4.ch11.l1101;

import java.util.List;

public interface CarService {

	List<Car> findAll();
	Car save(Car car);
	void updateCarAgeJob();
	
}

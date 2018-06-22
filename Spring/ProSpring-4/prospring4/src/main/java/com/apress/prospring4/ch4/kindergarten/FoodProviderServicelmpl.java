package com.apress.prospring4.ch4.kindergarten;

import java.util.ArrayList;
import java.util.List;
import com.apress.prospring4.ch4.Food;
import com.apress.prospring4.ch4.FoodProviderService;

public class FoodProviderServicelmpl implements FoodProviderService {

	@Override
	public List<Food> provideLunchSet() {
		List<Food> lunchSet = new ArrayList<>();
		lunchSet.add(new Food("Milk"));
		lunchSet.add(new Food("Bisquits"));
		return lunchSet;
	}

}

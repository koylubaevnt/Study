package com.koylubaevnt.stepik.java.basecourse.implement;

import java.time.LocalDate;

public interface OrderService {
	
	Order[] getOrdersByClient(long clientId);
	
	default Order[] getOrdersByClient(long clientId, LocalDate date) {
		Order[] allOrders = getOrdersByClient(clientId);
		return Orders.filterByDate(allOrders, date);
	}
}

package com.apress.prospring4.ch17;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

	private TaskScheduler taskScheduler;
	private SimpMessagingTemplate simpMessagingTemplate;
	private List<Stock> stocks = new ArrayList<Stock>();
	private Random random = new Random(System.currentTimeMillis());
	
	public StockController() {
		stocks.add(new Stock("VMW", 1.00d));
		stocks.add(new Stock("EMC", 1.00d));
		stocks.add(new Stock("GOOG", 1.00d));
		stocks.add(new Stock("IBM", 1.00d));
	}
	
	@Autowired
	public void setSimpMessagingTemplate(SimpMessagingTemplate simpMessagingTemplate) {
		this.simpMessagingTemplate = simpMessagingTemplate;
	}
	
	@Autowired
	public void setTaskScheduler(TaskScheduler taskScheduler) {
		this.taskScheduler = taskScheduler;
	}
	
	@MessageMapping("/addStock")
	public void addStock(Stock stock) {
		stocks.add(stock);
		broadcastUpdatedPrices();
	}

	@PostConstruct
	private void broadcastTimePeriodically() {
		taskScheduler.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				broadcastUpdatedPrices();
			}
		}, 1000);
	}
	
	private void broadcastUpdatedPrices() {
		stocks.stream().forEach(e -> {
			e.setPrice(e.getPrice() + getUpdatedStockPrice() * e.getPrice());
			e.setDate(new Date());
		});
		simpMessagingTemplate.convertAndSend("/topic/price", stocks);
	}

	private double getUpdatedStockPrice() {
		double priceChange = random.nextDouble() * 5.0d;
		if(random.nextInt(2) == 1) {
			priceChange = -priceChange;
		}
		return priceChange / 100.0d;
	}
}

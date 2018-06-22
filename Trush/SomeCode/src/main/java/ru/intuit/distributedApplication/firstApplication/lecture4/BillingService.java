package ru.intuit.distributedApplication.firstApplication.lecture4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Серверный класс <code>BillingService</code> создает серверный сокет, после чего 
 * ждет подключение клиента.<br/>
 * При подключении клиента создается экземпляр класса {@link BillingClientService},
 * в конструктор которого передаются {@link java.io.InputStream} и {@link java.io.OutputStream}<br/>
 * <code>BillingService</code> должен уметь одновременно обрабатывать соединения с несколькими 
 * клиентами.<p/>
 * 
 * Основной задачей системы является выполнение операций по обслуживанию клиентских карт, класс 
 * <code>BillingService</code> предлагает соответствующие методы - всего их четыре:
 * <ul>
 * <li><code><strong>void addNewCard(String personName, String card)</strong></code> - добавляет в систему новую карту, с идентификатором card и personName - ФИО пользователя;</li>
 * <li><code><strong>void addMoney(String card, double money)</strong></code> - увеличивает остаток на карте card на величину money (пополнение счета);</li>
 * <li><code><strong>void subMoney(String card, double money)</strong></code> - уменьшает остаток на карте card на величину money (оплата картой);</li>
 * <li><code><strong>double getCardBalance(String card)</strong></code> - для карты с идентификатором card возвращает значение баланса.</li><p/>
 * 
 * Для хранения "базы" карт применяется хэш-таблица, в которой ключом служит идентификатор карты, а в 
 * качестве значения выступает ее баланс. Соответственно, все операции над картой в качестве обязательного 
 * параметра содержат ее идентификатор, используя который в качестве ключа, получают доступ к балансу.
 * 
 * @author KojlubaevNT
 * @version 1.0.0
 */
public class BillingService extends Thread {

	private static final Logger logger = LoggerFactory.getLogger(BillingService.class);
	private static final int PORT = 7896;
	private static final String HOST = "localhost";
	
	private Map<String, Card> hash;
	
	public BillingService() {
		hash = new ConcurrentHashMap<>();
	}
	
	public static void main(String[] args) {
		BasicConfigurator.configure();
		BillingService billingService = new BillingService();
		billingService.start();
	}
	
	@Override
	public void run() {
		try(ServerSocket serverSocket = new ServerSocket(PORT)) {
			logger.info("started");
			while(true) {
				logger.debug("Waiting client...");
				Socket socket = serverSocket.accept();
				logger.debug("Client accepted.");
				BillingClientService billingClientService 
					//= new BillingClientService(this, socket.getInputStream(), socket.getOutputStream());
				= new BillingClientService(this, socket);
				logger.debug("BillingClientService created.");
				billingClientService.start();
			}
		} catch (IOException e) {
			logger.error("error " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void addNewCard(Card card) {
		hash.put(card.getCard(), card);
	} 
	
	public void addMoney(String card, double money) {
		Card c = hash.get(card);
		if(c == null) {
			logger.debug("Bad card number");
			return;
		}
		c.setBalance(c.getBalance() + money);
		hash.put(card, c);
	}
	 
	public void subMoney(String card, double money) {
		Card c = hash.get(card);
		if(c == null) {
			logger.debug("Bad card number");
			return;
		}
		c.setBalance(c.getBalance() - money);
		hash.put(card, c);
	}
	 
	public Card getCard(String card) {
		return hash.get(card);
	}
	/*
	public void addNewCard(String personName, String card) {
		hash.put(card, Double.valueOf(0.0));
	}
	
	 public void addMoney(String card, double money) {
		 Double d = hash.get(card);
		 if(d != null) {
			 hash.put(card, Double.valueOf(d.doubleValue() + money));
		 }
	 }
	 
	 public void subMoney(String card, double money) {
		 Double d = hash.get(card);
		 if(d != null) {
			 hash.put(card, Double.valueOf(d.doubleValue() - money));
		 }	 
	 }
	 
	 public double getCardBalance(String card) {
		 Double d = hash.get(card);
		 return (d == null) ? 0.0 : d.doubleValue();
	 }
	 */
}

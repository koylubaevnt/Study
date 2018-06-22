package ru.intuit.distributedApplication.firstApplication.lecture4;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  Серверный класс <code>BillingClientService</code> отвечает непосредственно за 
 *  взаимодействие с клиентом.<br/>
 *  Получив в конструкторе два потока - поток ввода и поток вывода, он способен с их помощью 
 *  как принимать данные, отправляемые клиентом, так и посылать ему данные.<p/>
 *  Построим обработку клиентского соединения следующим образом: в цикле читаем из потока 
 *  целое число, которое интерпретируем как код операции, и, в зависимости от прочитанного 
 *  кода операции, вызываем соответствующий метод.<p/> 
 *  Вызываемый метод выполняет следующие действия:
 *  <ul>
 *  <li>дочитывает из потока необходимые для работы параметры (поскольку это конкретный метод, например, увеличения баланса карты, список параметров для него известен);</li>
 *  <li>вызывает соответствующий метод BillingService,передавая ему прочитанные параметры, который и выполняет все необходимые действия.</li>
 *  </ul>
 * 
 * 
 * @author KojlubaevNT
 * @version 1.0.0
 *
 */
public class BillingClientService extends Thread{

	private static final Logger logger = LoggerFactory.getLogger(BillingClientService.class);
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private final BillingService billingService;
	private final Socket socket;
	
	public BillingClientService(BillingService billingService, Socket socket) {
		//BasicConfigurator.configure();
		
		logger.debug("Constructor");
		this.billingService = billingService;
		this.socket = socket;
		try {
			/*Выяснить почему если поменять in и out местами - перестает работать сервер - не проходит далее инициализация*/
			logger.debug("init out");
			this.out = new ObjectOutputStream(socket.getOutputStream());
			logger.debug("init in");
			this.in = new ObjectInputStream(socket.getInputStream());
			logger.debug("init done");
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.debug("Stream's done. Socket: " + socket);
	}
	/*public BillingClientService(BillingService billingService, InputStream in, OutputStream out) {
		this.billingService = billingService;
		this.in = new DataInputStream(in);
		this.out = new DataOutputStream(out);
	}*/
	
	@Override
	public void run() {
		logger.debug("Thread started");
		boolean work = true;
		while(work) {
			BillingOperationType operationType;
			Object o;
			try {
				o = in.readObject();
				if(o instanceof Card[]) {
					Card[] cards = (Card[]) o;
					for(int i = 0; i < cards.length; i++) {
						billingService.addNewCard(cards[i]);
					}
				} else if(o instanceof CardOperation[]) {
					CardOperation[] cardOperations = (CardOperation[]) o;
					for(int i = 0; i < cardOperations.length; i++) {
						switch(cardOperations[i].getOperationType()) {
							case ADD_MONEY:
								billingService.addMoney(cardOperations[i].getCard(), cardOperations[i].getAmount());
								break;
							case SUB_MONEY:
								billingService.subMoney(cardOperations[i].getCard(), cardOperations[i].getAmount());
								break;
							default:
								logger.info("Bad operation");
								break;
						}
					}
				} else if (o instanceof String) {
					String command = (String) o;
					//logger.debug("BillingClientService:  instanceof String " + command);
					if(command.equals(BillingOperationType.EXIT_CLIENT.toString())) {
						work = false;
					} else {
						out.writeObject(billingService.getCard((String) o));
					}
				}
			} catch (IOException | ClassNotFoundException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
				work = false;
			}
		}
	}
	/*
	@Override
	public void run() {
		logger.debug("BillingClientService: Thread started");
		boolean work = true;
		while(work) {
			BillingOperationType operationType;
			try {
				operationType = BillingOperationType.valueOf(in.readUTF());
				switch (operationType) {
				case ADD_NEW_CARD:
					addNewCard();
					break;
				case ADD_MONEY:
					addMoney();
					break;
				case SUB_MONEY:
					subMoney();
					break;
				case GET_CARD_BALANCE:
					getCardBalance();
					break;
				case EXIT_CLIENT:
					work = false;
					break;

				default:
					logger.info("BillingClientService: Bad operation " + operationType);
					break;
				}
			} catch (IOException e) {
				logger.debug("BillingClientService: error " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	private void getCardBalance() throws IOException {
		String card = in.readUTF();
		double balance = billingService.getCardBalance(card);
		out.writeDouble(balance);
		out.flush();
	}
	 
	private void subMoney() throws IOException {
		String card = in.readUTF();
		double money= in.readDouble();
		billingService.subMoney(card, money);
	}

	private void addMoney() throws IOException {
		String card = in.readUTF();
		double money= in.readDouble();
		billingService.addMoney(card, money);
		
	}

	private void addNewCard() throws IOException {
		String personName = in.readUTF();
		String card = in.readUTF();
		billingService.addNewCard(personName, card);
		
	}
	*/
}

package ru.intuit.distributedApplication.firstApplication.lecture4;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

/**
 * <code>BillingClient</code> это класс для клиента.
 * Клиентское приложение устроено очень просто. Сетевое имя сервера принимает в качестве аргумента 
 * командной строки, порт известен заранее. </p>
 * <ul>
 * <li>Первое, что делается, - устанавливается соединение с сервером (метод {@link #connectToServer}).</li>
 * <li>Затем создаются три новые карты с разными идентификаторами. Создаются они с помощью метода 
 * {@link #sendNewCardOperation}, который действует в соответствии с установленным протоколом: 
 * он записывает в поток сначала код операции, затем имя владельца карты, затем его идентификатор.
 * <li>После создания карт в цикле выполняется метод {@link #sendAddMoneyOperation},который посылает 
 * серверу команды об увеличении баланса для только, что добавленных карт. Цикл выполняется 1000 раз, 
 * поэтому средств на каждой из карт в итоге оказывается много.</li>
 * <li>С помощью вызовов {@link #sendGetCardBalanceOperation} в качестве параметра, которого передается 
 * идентификатор карты, мы можем в этом убедиться.</li>
 * </ul>
 * Все реализованные в клиентском приложении методы "симметричны" соответствующим методам 
 * {@link BillingClientService} и неукоснительно соблюдают разработанный протокол:
 * <ul> 
 * <li>{@link #sendNewCardOperation},</li>
 * <li>{@link #sendAddMoneyOperation},</li>
 * <li>{@link #sendSubMoneyOperation},</li> 
 * <li>{@link #sendGetCardBalanceOperation},</li> 
 * <li>{@link #closeConnection}</li>
 * </ul>
 *   
 * @author KojlubaevNT
 * @version 1.0.0
 *
 */
public class BillingClient {

	//private static final Logger logger = LoggerFactory.getLogger(BillingClient.class);
	
	private static final int PORT = 7896;
	String serverName;
	Socket socket;
	//DataInputStream in;
	//DataOutputStream out;
	ObjectInputStream in;
	ObjectOutputStream out;
	
	public BillingClient(String serverName) {
		this.serverName = serverName;
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		//BasicConfigurator.configure();
		
		BillingClient billingClient = new BillingClient(args[0]);
		billingClient.startTest();
	}

	public void startTest() throws UnknownHostException, IOException, InterruptedException {
		System.out.println("BillingClient: start");
		connectToServer();
		System.out.println("BillingClient: start");
		Card[] cards = {
			new Card("Peter", LocalDate.now(), "1", 0.0),
			new Card("Stefan", LocalDate.now(), "2", 0.0),
			new Card("Nataly", LocalDate.now(), "3", 0.0)
		};
		processCard(cards);
		
		final int cnt = 30000;
		CardOperation[] cardOperations = new CardOperation[cnt];
		for(int i = 0; i < cnt; i++) {
			cardOperations[i] = new CardOperation(String.valueOf(i % 3 + 1), i % 3 + 1, BillingOperationType.ADD_MONEY, LocalDate.now());
		}
		processOperation(cardOperations);
		
		cardOperations = new CardOperation[3];
		cardOperations[0] = new CardOperation("1", 100, BillingOperationType.SUB_MONEY, LocalDate.now());
		cardOperations[1] = new CardOperation("2", 1000, BillingOperationType.SUB_MONEY, LocalDate.now());
		cardOperations[2] = new CardOperation("3", 10000, BillingOperationType.SUB_MONEY, LocalDate.now());
		processOperation(cardOperations);
		
		
		try {
			System.out.println("BillingClient: getCard 1 " + getCard("1"));
			System.out.println("BillingClient: getCard 2 " + getCard("2"));
			System.out.println("BillingClient: getCard 3 " + getCard("3"));
			/*
			logger.info("BillingClient: getCard 1" + getCard("1"));
			logger.info("BillingClient: getCard 2" + getCard("2"));
			logger.info("BillingClient: getCard 3" + getCard("3"));
			*/
		}catch (Exception e) {
			e.printStackTrace();
		}
		closeConnection();
		TimeUnit.SECONDS.sleep(2);
	}
	
	public void closeConnection() throws IOException {
		System.out.println("BillingClient: closeConnection ");
		String exit = BillingOperationType.EXIT_CLIENT.toString();
		out.writeObject(exit);
	}
	
	private Card getCard(String card) throws IOException, ClassNotFoundException {
		System.out.println("BillingClient: getCard " + card);
		out.writeObject(card);
		return (Card) in.readObject();
	}

	private void processOperation(CardOperation[] cardOperations) throws IOException {
		//logger.debug("BillingClient: processOperation " + cardOperations);
		System.out.println("BillingClient: processOperation " + cardOperations);
		out.writeObject(cardOperations);	
	}

	private void processCard(Card[] cards) throws IOException {
		//logger.debug("BillingClient: processCard " + cards);
		System.out.println("BillingClient: processCard " + cards);
		out.writeObject(cards);		
	}

	public void connectToServer() throws UnknownHostException, IOException {
		System.out.println("BillingClient: connect to Server");
		socket = new Socket(serverName, PORT);
		System.out.println("BillingClient: connect to Server establishment");
		in = new ObjectInputStream(socket.getInputStream());
		out = new ObjectOutputStream(socket.getOutputStream());
		System.out.println("BillingClient: connected to Server");
	}
	
	/*
	public void startTest() throws UnknownHostException, IOException {
		connectToServer();
		sendNewCardOperation("Peter", "1");
		sendNewCardOperation("Stefan", "2");
		sendNewCardOperation("Nataly", "3");
		for(int i = 0; i <= 1000; i++) {
			sendAddMoneyOperation("1", i%10);
			sendAddMoneyOperation("2", i%20);
			sendAddMoneyOperation("3", i%30);
		}
		logger.info("1:" + sendGetCardBalanceOperation("1"));
		logger.info("2:" + sendGetCardBalanceOperation("2"));
		logger.info("3:" + sendGetCardBalanceOperation("3"));
		closeConnection();
	}
	
	public void connectToServer() throws UnknownHostException, IOException {
		socket = new Socket(serverName, PORT);
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
	}
	
	public void sendNewCardOperation(String userName, String card) throws IOException {
		out.writeUTF(BillingOperationType.ADD_NEW_CARD.toString());
		out.writeUTF(userName);
		out.writeUTF(card);
	}
	public void sendAddMoneyOperation(String card, double money) throws IOException {
		out.writeUTF(BillingOperationType.ADD_MONEY.toString());
		out.writeUTF(card);
		out.writeDouble(money);
	}
	public void sendSubMoneyOperation(String card, double money) throws IOException {
		out.writeUTF(BillingOperationType.SUB_MONEY.toString());
		out.writeUTF(card);
		out.writeDouble(money);		
	}
	
	public double sendGetCardBalanceOperation(String card) throws IOException {
		out.writeUTF(BillingOperationType.GET_CARD_BALANCE.toString());
		out.writeUTF(card);
		return in.readDouble();
	}
	public void closeConnection() throws IOException {
		out.writeUTF(BillingOperationType.EXIT_CLIENT.toString());
	}
	*/
}

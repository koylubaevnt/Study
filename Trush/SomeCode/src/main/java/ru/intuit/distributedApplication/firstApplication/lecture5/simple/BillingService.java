package ru.intuit.distributedApplication.firstApplication.lecture5.simple;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Удаленные методы, посредством которых клиент взаимодействует с удаленным объектом, используя RMI, 
 * должны быть определены в удаленном интерфейсе. <p/>
 * Соответственно, первый этап при создании распределенного приложения с помощью RMI состоит в 
 * определении удаленного интерфейса, который описывает эти удаленные методы.<br/>
 * Чтобы создать удаленный интерфейс, необходимо определить интерфейс, который будет расширять интерфейс 
 * {@link java.rmi.Remote}.<br/>
 * Распределенное RMI -приложение должно экспортировать объект класса, который реализует интерфейс 
 * <code>Remote</code>, чтобы сделать этот удаленный объект доступным для приема удаленных вызовов 
 * метода из любой виртуальной машины Java, которая имеет соединение с компьютером, где выполняется 
 * удаленный объект.<p/>
 * 
 * RMI использует механизм сериализации по умолчанию Java для передачи параметров методу и возврата 
 * значений через сеть. В связи с этим все параметры метода и возвращаемые значения должны иметь 
 * описатель Serializable или один из примитивных типов.
 * 
 * @author KojlubaevNT
 *
 */
public interface BillingService extends Remote {
	
	// Добавление новой карты
	public void addNewCard(String personName, String card) throws RemoteException;
	
	// Добавление денежных средств на карту
	public void addMoney(String card, double money) throws RemoteException;
	
	// Снятие денежных средств с карты
	public void subMoney(String card, double money) throws RemoteException;
	
	// Получение баланса карты
	public double getCardBalance(String card) throws RemoteException;
	
}

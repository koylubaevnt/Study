package com.koylubaevnt.stepik.java.basecourse.loggers;

/**
 * Thief – вор, который ворует самые ценные посылки и игнорирует все остальное. Вор принимает в конструкторе 
 * переменную int – минимальную стоимость посылки, которую он будет воровать. Также, в данном классе должен 
 * присутствовать метод getStolenValue, который возвращает суммарную стоимость всех посылок, которые он своровал. 
 * Воровство происходит следующим образом: вместо посылки, которая пришла вору, он отдает новую, такую же, 
 * только с нулевой ценностью и содержимым посылки "stones instead of {content}".
 * 
 * @author koylu
 *
 */
public class Thief implements MailService {

	private int minCost;
	private int stolenValue;
	
	public Thief(int minCost) {
		this.minCost = minCost;
	}
	
	public int getStolenValue() {
		return stolenValue;
	}
	
	@Override
	public Sendable processMail(Sendable mail) {
		Sendable processedMail = mail;
		if(mail instanceof MailPackage) {
			MailPackage mailPackage = (MailPackage) mail;
			int cost = mailPackage.getContent().getPrice(); 
			if(cost >= minCost) {
				stolenValue += cost;
				Package newPackage = new Package("stones instead of " + mailPackage.getContent().getContent(), 0);
				processedMail = new MailPackage(mailPackage.getFrom(), mailPackage.getTo(), newPackage);
			}
		}
		return processedMail;
	}

}

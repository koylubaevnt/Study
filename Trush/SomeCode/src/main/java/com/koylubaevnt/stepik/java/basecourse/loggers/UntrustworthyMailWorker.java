package com.koylubaevnt.stepik.java.basecourse.loggers;

/**
 * UntrustworthyMailWorker – класс, моделирующий ненадежного работника почты, который вместо того, чтобы передать 
 * почтовый объект непосредственно в сервис почты, последовательно передает этот объект набору третьих лиц, а затем, 
 * в конце концов, передает получившийся объект непосредственно экземпляру RealMailService. 
 * У UntrustworthyMailWorker должен быть конструктор от массива MailService ( результат вызова processMail первого 
 * элемента массива передается на вход processMail второго элемента, и т. д.) и метод getRealMailService, который 
 * возвращает ссылку на внутренний экземпляр RealMailService.
 * 
 * @author koylu
 *
 */
public class UntrustworthyMailWorker implements MailService{

	private MailService[] mailServices;
	private RealMailService realMailService = new RealMailService();
	
	public UntrustworthyMailWorker(MailService[] mailServices) {
		this.mailServices = mailServices;
	}

	@Override
	public Sendable processMail(Sendable mail) {
		Sendable untrustMail = mail;
		for (MailService mailService : mailServices) {
			untrustMail = mailService.processMail(untrustMail);
		}
		return realMailService.processMail(untrustMail);
	}
	
	public RealMailService getRealMailService() {
		return realMailService;
	}
}

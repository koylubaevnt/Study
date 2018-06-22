package com.koylubaevnt.stepik.java.basecourse.loggers;

/**
 * Inspector – Инспектор, который следит за запрещенными и украденными посылками и бьет тревогу в виде исключения, 
 * если была обнаружена подобная посылка. Если он заметил запрещенную посылку с одним из запрещенных содержимым 
 * ("weapons" и "banned substance"), то он бросает IllegalPackageException. Если он находит посылку, состоящую 
 * из камней (содержит слово "stones"), то тревога прозвучит в виде StolenPackageException. Оба исключения вы 
 * должны объявить самостоятельно в виде непроверяемых исключений.
 * 
 * @author koylu
 *
 */
public class Inspector implements MailService{

	public static final String WEAPONS = "weapons";
	public static final String BANNED_SUBSTANCE = "banned substance";
	
	
	@Override
	public Sendable processMail(Sendable mail) {
		if(mail instanceof MailPackage) {
			MailPackage mailPackage = (MailPackage) mail;
			if (mailPackage.getContent().getContent().equals(WEAPONS) || mailPackage.getContent().getContent().equals(BANNED_SUBSTANCE)) {
				throw new IllegalPackageException(WEAPONS + " or " + BANNED_SUBSTANCE + " in mail!");
			} else if (mailPackage.getContent().getContent().contains("stones")) {
				throw new StolenPackageException("Stones in mail!");
			}
		}
		return mail;
	}

}

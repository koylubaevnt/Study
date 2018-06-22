package com.brysekkel.typeinfo.enumerated;

import java.util.Iterator;

import javax.persistence.EnumType;

import com.brysekkel.utils.notmine.Enums;

class Mail {
	enum GeneralDelivery { YES, NO1, NO2, NO3, NO4, NO5 }
	enum Scannability { UNSCANNABLE, YES1, YES2, YES3, YES4 }
	enum Readability { ILLEGIBLE, YES1, YES2, YES3, YES4 }
	enum Address { INCORRECT, OK1, OK2, OK3, OK4, OK5, OK6 }
	enum ReturnAddress { MISSING, OK1, OK2,  OK3,  OK4, OK5 }
	
	GeneralDelivery generalDelivery;
	Scannability scannability;
	Readability readability;
	Address address;
	ReturnAddress returnAddress;
	static long counter = 0;
	long id = counter++;
	@Override
	public String toString() { return "Mail " + id;	}
	
	public String details() {
		return toString() + 
			", GeneralDelivery: " + generalDelivery +
			", Scannability: " + scannability +
			", Readability: " + readability +
			", Address: " + address +
			", ReturnAddress: " + returnAddress;
	}
	
	public static Mail randomMail() {
		Mail mail = new Mail();
		mail.generalDelivery = Enums.random(GeneralDelivery.class);
		mail.scannability = Enums.random(Scannability.class);
		mail.readability = Enums.random(Readability.class);
		mail.address = Enums.random(Address.class);
		mail.returnAddress = Enums.random(ReturnAddress.class);
		return mail;
	}
	
	public static Iterable<Mail> generator(final int count) {
		return new Iterable<Mail>() {
			int n = count;
			@Override
			public Iterator<Mail> iterator() {
				return new Iterator<Mail>() {
					
					@Override
					public Mail next() {
						n--;
						return randomMail();
					}
					
					@Override
					public boolean hasNext() {
						return n > 0;
					}
					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}
}

public class PostOffice {

	enum MailHandler {
		GENERAL_DELIVERY {
			@Override
			boolean handle(Mail m) {
				switch (m.generalDelivery) {
				case YES:
					System.out.println("Using general delivery for " + m);
					return true;
				default: return false;
				}
			}
		},
		MACHINE_SCAN {
			@Override
			boolean handle(Mail m) {
				switch (m.scannability) {
				case UNSCANNABLE:
					return false;
				default:
					switch (m.address) {
					case INCORRECT:
						return false;

					default:
						System.out.println("Delivering " + m + " automaticaly");
						return true;
					}
				}
			}
		},
		VISUAL_INSPECTION {
			@Override
			boolean handle(Mail m) {
				switch (m.readability) {
				case ILLEGIBLE:
					return false;
				default:
					switch (m.address) {
					case INCORRECT:
						return false;

					default:
						System.out.println("Delivering " + m + " normaly");
						return true;
					}
				}
			}
		},
		RETURN_TO_SENDER {
			@Override
			boolean handle(Mail m) {
				switch (m.returnAddress) {
				case MISSING:
					return false;
				default:
					System.out.println("Returning " + m + " to sender");
					return true;
				}
			}
		};
		abstract boolean handle(Mail m);
	}
	
	static void handle(Mail m) {
		for(MailHandler handler : MailHandler.values())
			if(handler.handle(m))
				return;
		System.out.println(m + " is a dead letter");
	}
	
	public static void main(String[] args) {
		for(Mail mail : Mail.generator(10)) {
			System.out.println(mail.details());
			handle(mail);
			System.out.println("*****");
		}

	}

}

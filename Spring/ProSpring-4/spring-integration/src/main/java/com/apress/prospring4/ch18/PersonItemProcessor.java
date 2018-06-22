package com.apress.prospring4.ch18;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

	private static final Log LOG = LogFactory.getLog(PersonItemProcessor.class);
	
	@Override
	public Person process(Person item) throws Exception {
		String firstName = item.getFirstName().toUpperCase();
		String lastName = item.getLastName().toUpperCase();
		Person transformedPerson = new Person();
		transformedPerson.setFirstName(firstName);
		transformedPerson.setLastName(lastName);
		LOG.info("Transformed person: " + item + " into: " + transformedPerson);
		return transformedPerson;
	}

}

package com.apress.prospring4.ch10.l101;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class SpringValidatorSample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch10/l101/spring-validator-app-context.xml");
		context.refresh();
		
		Contact contact = new Contact();
		contact.setFirstName(null);
		contact.setLastName("Schaefer");
		Validator validator = context.getBean("contactValidator", Validator.class);
		
		BeanPropertyBindingResult result = 
				new BeanPropertyBindingResult(contact, "Chris");
		ValidationUtils.invokeValidator(validator, contact, result);
		List<ObjectError> errors = result.getAllErrors();
		System.out.println("No of validation errors: " + errors.size());
		for(ObjectError error : errors) {
			System.out.println(error.getCode());
		}
		
		
		
	}

}

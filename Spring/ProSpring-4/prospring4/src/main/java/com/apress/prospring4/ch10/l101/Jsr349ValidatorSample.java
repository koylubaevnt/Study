package com.apress.prospring4.ch10.l101;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.context.support.GenericXmlApplicationContext;



public class Jsr349ValidatorSample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch10/l101/jsr349-app-context.xml");
		context.refresh();
		
		MyBeanValidationService validation =
				context.getBean("myBeanValidationService", MyBeanValidationService.class);
		
		Customer customer = new Customer();
		customer.setFirstName("C");
		customer.setLastName("Schaefer");
		customer.setCustomerType(null);
		customer.setGender(null);
		
		validateCustomer(customer, validation);
		
			
	}
	
	private static void validateCustomer(Customer customer, MyBeanValidationService validation) {
		Set<ConstraintViolation<Customer>> violations =
				new HashSet<>();
		violations = validation.validateCustomer(customer);
		
		listViolations(violations);
	}

	private static void listViolations(Set<ConstraintViolation<Customer>> violations) {
		System.out.println("No of violations: " + violations.size());
		
		for(ConstraintViolation<Customer> violation : violations) {
			System.out.println("Validation error for property: " + violation.getPropertyPath() +
					" with value: " + violation.getInvalidValue() +
					" with error message: " + violation.getMessage());
		}
		
	}
}

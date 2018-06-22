package com.apress.prospring4.ch10.l101;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.context.support.GenericXmlApplicationContext;



public class Jsr349AssertValidatorSample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch10/l101/jsr349-app-context.xml");
		context.refresh();
		
		MyBeanValidationService2 validation =
				context.getBean("myBeanValidationService2", MyBeanValidationService2.class);
		
		Customer2 customer = new Customer2();
		customer.setFirstName("Chris");
		customer.setLastName("Schaefer");
		customer.setCustomerType(CustomerType.INDIVIDUAL);
		customer.setGender(null);
		
		validateCustomer(customer, validation);
		
			
	}
	
	private static void validateCustomer(Customer2 customer, MyBeanValidationService2 validation) {
		Set<ConstraintViolation<Customer2>> violations =
				new HashSet<>();
		violations = validation.validateCustomer(customer);
		
		listViolations(violations);
	}

	private static void listViolations(Set<ConstraintViolation<Customer2>> violations) {
		System.out.println("No of violations: " + violations.size());
		
		for(ConstraintViolation<Customer2> violation : violations) {
			System.out.println("Validation error for property: " + violation.getPropertyPath() +
					" with value: " + violation.getInvalidValue() +
					" with error message: " + violation.getMessage());
		}
		
	}
}

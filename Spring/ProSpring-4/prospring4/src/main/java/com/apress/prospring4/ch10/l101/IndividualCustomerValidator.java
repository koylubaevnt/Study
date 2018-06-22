package com.apress.prospring4.ch10.l101;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IndividualCustomerValidator implements ConstraintValidator<CheckIndividualCustomer, Customer> {

	@Override
	public void initialize(CheckIndividualCustomer constraintAnnotation) {
	}

	@Override
	public boolean isValid(Customer value, ConstraintValidatorContext context) {
		boolean result = true;
		if(value.getCustomerType() != null && value.isIndividualCustomer() && 
				(value.getLastName() == null || value.getGender() == null)) {
			result = false;
		}
		return result;
	}

}

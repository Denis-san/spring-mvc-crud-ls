package br.com.san.ls.validation;

import java.util.Calendar;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class YearConstraintValidator implements ConstraintValidator<Year, Integer> {

	private Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);

	@Override
	public void initialize(Year constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if (value != null) {
			return (value >= 1500 && value <= currentYear);
		} else {
			return false;
		}
	}

}

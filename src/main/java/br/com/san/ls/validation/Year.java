package br.com.san.ls.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = YearConstraintValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Year {
	
	public int value() default 2022;
	
	public String message() default "Deve estar entre 1500 e o ano atual";
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default {};
	
}

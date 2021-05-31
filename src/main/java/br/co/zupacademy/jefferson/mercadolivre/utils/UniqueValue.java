package br.co.zupacademy.jefferson.mercadolivre.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)	
public @interface UniqueValue {
	
	String message() default "{br.com.zup.academy.jefferson.beanvalidator.uniquevalue}";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
	String fieldName();
	
	Class<?> domainClass();
	
}
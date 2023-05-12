package com.example.kakeibo.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class SpendingTest {

	private Validator validator;
	
	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void testValidationSuccess() {
		Spending spe = new Spending();
		spe.setSpendingAmount(1000);
		spe.setDatetime(LocalDate.of(1996, 9, 17));
		spe.setMemo("おはよう");
		
		Set<ConstraintViolation<Spending>> violations = validator.validate(spe);
		assertThat(violations.size()).isEqualTo(0);
	}
	
	@Test
	public void testValidationFailSpendingAmount() {
		Spending spe = new Spending();
		spe.setDatetime(LocalDate.of(1996, 9, 17));
		spe.setMemo("おはよう");
		
		Set<ConstraintViolation<Spending>> violations = validator.validate(spe);
		assertThat(violations.size()).isEqualTo(1);
		for(ConstraintViolation<Spending> violation:violations) {
			Object annotation = violation.getConstraintDescriptor().getAnnotation();
			assertThat(annotation).isInstanceOf(NotNull.class);
		}
	}
	
	@Test
	public void testValidationFailSpendingAmountSize() {
		Spending spe = new Spending();
		spe.setSpendingAmount(1234567890);
		spe.setDatetime(LocalDate.of(1996, 9, 17));
		spe.setMemo("おはよう");
		
		Set<ConstraintViolation<Spending>> violations = validator.validate(spe);
		assertThat(violations.size()).isEqualTo(1);
		for(ConstraintViolation<Spending> violation:violations) {
			Object annotation = violation.getConstraintDescriptor().getAnnotation();
			assertThat(annotation).isInstanceOf(Max.class);
		}
	}
	
	@Test
	public void testValidationFailDatetime() {
		Spending spe = new Spending();
		spe.setSpendingAmount(1000);
		spe.setMemo("おはよう");
		
		Set<ConstraintViolation<Spending>> violations = validator.validate(spe);
		assertThat(violations.size()).isEqualTo(1);
		for(ConstraintViolation<Spending> violation:violations) {
			Object annotation = violation.getConstraintDescriptor().getAnnotation();
			assertThat(annotation).isInstanceOf(NotNull.class);
		}
	}
	
	@Test
	public void testValidationFailMemo() {
		Spending spe = new Spending();
		spe.setSpendingAmount(1000);
		spe.setDatetime(LocalDate.of(1996, 9, 17));
		
		Set<ConstraintViolation<Spending>> violations = validator.validate(spe);
		assertThat(violations.size()).isEqualTo(1);
		for(ConstraintViolation<Spending> violation:violations) {
			Object annotation = violation.getConstraintDescriptor().getAnnotation();
			assertThat(annotation).isInstanceOf(NotBlank.class);
		}
	}
	
	@Test
	public void testValidationFailMemoSize() {
		Spending spe = new Spending();
		spe.setSpendingAmount(1000);
		spe.setDatetime(LocalDate.of(1996, 9, 17));
		spe.setMemo("おはよう、こんにちは、こんばんは、おやすみなさい");
		
		Set<ConstraintViolation<Spending>> violations = validator.validate(spe);
		assertThat(violations.size()).isEqualTo(1);
		for(ConstraintViolation<Spending> violation:violations) {
			Object annotation = violation.getConstraintDescriptor().getAnnotation();
			assertThat(annotation).isInstanceOf(Size.class);
		}
	}
}

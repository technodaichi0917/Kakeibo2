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

public class IncomeTest {
	private Validator validator;
	
	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testValidationSuccess() {
		Income inc = new Income();
		inc.setIncomeAmount(1000);
		inc.setDatetime(LocalDate.of(1996, 9, 17));
		inc.setMemo("おはよう");
		
		Set<ConstraintViolation<Income>> violations = validator.validate(inc);
		assertThat(violations.size()).isEqualTo(0);
	}
	
	@Test
	public void testValidationFailIncomeAmount() {
		Income inc = new Income();
		inc.setDatetime(LocalDate.of(1996, 9, 17));
		inc.setMemo("おはよう");
		
		Set<ConstraintViolation<Income>> violations = validator.validate(inc);
		assertThat(violations.size()).isEqualTo(1);
		for (ConstraintViolation<Income> violation : violations) {
            Object annotation = violation.getConstraintDescriptor().getAnnotation();
            assertThat(annotation).isInstanceOf(NotNull.class);
        }
	}
	
	@Test
	public void testValidationFailIncomeAmountSize() {
		Income inc = new Income();
		inc.setDatetime(LocalDate.of(1996, 9, 17));
		inc.setMemo("おはよう");
		inc.setIncomeAmount(1234567890);
		Set<ConstraintViolation<Income>> violations = validator.validate(inc);
		assertThat(violations.size()).isEqualTo(1);
		for (ConstraintViolation<Income> violation : violations) {
            Object annotation = violation.getConstraintDescriptor().getAnnotation();
            assertThat(annotation).isInstanceOf(Max.class);
        }
	}
	
	@Test
	public void testValidationFailDateTime() {
		Income inc = new Income();
		inc.setMemo("おはよう");
		inc.setIncomeAmount(1000);
		Set<ConstraintViolation<Income>> violations = validator.validate(inc);
		assertThat(violations.size()).isEqualTo(1);
		for (ConstraintViolation<Income> violation : violations) {
            Object annotation = violation.getConstraintDescriptor().getAnnotation();
            assertThat(annotation).isInstanceOf(NotNull.class);
        }
	}
	
	@Test
	public void testValidationFailMemo() {
		Income inc = new Income();
		inc.setDatetime(LocalDate.of(1996, 9, 17));
		inc.setIncomeAmount(1000);
		Set<ConstraintViolation<Income>> violations = validator.validate(inc);
		assertThat(violations.size()).isEqualTo(1);
		for (ConstraintViolation<Income> violation : violations) {
            Object annotation = violation.getConstraintDescriptor().getAnnotation();
            assertThat(annotation).isInstanceOf(NotBlank.class);
        }
	}
	
	@Test
	public void testValidationFailMemoSize() {
		Income inc = new Income();
		inc.setDatetime(LocalDate.of(1996, 9, 17));
		inc.setIncomeAmount(1000);
		inc.setMemo("おはよう、こんにちは、こんばんは、おやすみなさい");
		Set<ConstraintViolation<Income>> violations = validator.validate(inc);
		assertThat(violations.size()).isEqualTo(1);
		for (ConstraintViolation<Income> violation : violations) {
            Object annotation = violation.getConstraintDescriptor().getAnnotation();
            assertThat(annotation).isInstanceOf(Size.class);
        }
	}
	
}

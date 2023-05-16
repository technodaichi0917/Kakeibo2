package com.exmample.kakeibo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.kakeibo.model.Income;
import com.example.kakeibo.repository.IncomeRepository;

@DataJpaTest
public class IncomeRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private IncomeRepository incomeRepository;
	
	@BeforeEach
	public void setUp(){
		
		LocalDate start = LocalDate.of(1600, 1, 1);
		LocalDate end = LocalDate.of(2023, 1, 1);
		
		Income income1 = new Income();
		income1.setIncomeAmount(1000);
		income1.setDatetime(LocalDate.of(1996, 9, 17));
		income1.setMemo("おはよう");
		entityManager.persist("income1");

		Income income2 = new Income();
		income2.setIncomeAmount(1000);
		income2.setDatetime(LocalDate.of(1996, 8, 17));
		income2.setMemo("こんにちは");
		entityManager.persist("income2");
		
		Income income3 = new Income();
		income3.setIncomeAmount(1000);
		income3.setDatetime(LocalDate.of(1996, 7, 17));
		income3.setMemo("こんばんは");
		entityManager.persist("income3");
	}
	
	@Test
	public void testFindAllByBetween() {
		LocalDate start = LocalDate.of(1600, 1, 1);
		LocalDate end = LocalDate.of(2023, 1, 1);
		List<Income> incomes = incomeRepository.findAllByDatetimeBetween(start, end);
		//assertThat(incomes).isNotNull;
	}

	
	
	
}

package com.example.kakeibo.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.kakeibo.model.Income;
import com.example.kakeibo.repository.IncomeRepository;

@SpringJUnitConfig
public class IncomeServiceTest {
	
	@TestConfiguration
	static class Config{
		@Bean
		public IncomeService incomeService() {
			return new IncomeService();
		}	
	}
	
	@Autowired
	private IncomeService incomeService;
	
	@MockBean
	private IncomeRepository incomeRepository;
	
	@Test
	public void testUpdateIncome() {
		Income income1 = new Income();
		incomeService.updateIncome(income1);
		verify(incomeRepository).save(income1);
	}

}

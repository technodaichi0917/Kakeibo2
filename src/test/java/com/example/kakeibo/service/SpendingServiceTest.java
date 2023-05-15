package com.example.kakeibo.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.kakeibo.model.Spending;
import com.example.kakeibo.repository.SpendingRepository;

@SpringJUnitConfig
public class SpendingServiceTest {

	@TestConfiguration
	static class Config{
		@Bean
		public SpendingService spendingService() {
			return new SpendingService();
		}
	}
	
	@Autowired
	private SpendingService spendingService;
	
	@MockBean
	private SpendingRepository spendingRepository;
	
	@Test
	public void testUpdateSpending() {
		Spending spending1 = new Spending();
		spendingService.updateSpending(spending1);
		verify(spendingRepository).save(spending1);
	}
}

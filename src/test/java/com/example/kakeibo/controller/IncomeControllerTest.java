package com.example.kakeibo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.kakeibo.repository.IncomeRepository;
import com.example.kakeibo.service.IncomeService;

@WebMvcTest(IncomeController.class)
public class IncomeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IncomeRepository incomeRepository;
	
	@MockBean
	private IncomeService incomeService;
	
	@Test
	@WithMockUser
	public void getNewIncomeTest() throws Exception {
		mockMvc.perform(get("/newIncome"))
			.andExpect(status().isOk())
			.andExpect(view().name("newIncome"));
	}
}

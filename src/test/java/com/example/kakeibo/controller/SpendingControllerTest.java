package com.example.kakeibo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.kakeibo.repository.SpendingRepository;
import com.example.kakeibo.service.SpendingService;

@WebMvcTest(SpendingController.class)
public class SpendingControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SpendingService spendingService;
	
	@MockBean
	private SpendingRepository spendingRepository;
	
	@Test
	@WithMockUser
	public void spendingCreateGet() throws Exception{
		mockMvc.perform(get("/newSpending"))
		.andExpect(status().isOk())
		.andExpect(view().name("newSpending"));
		//.andExpect(model().attribute("nowDate", nowDate));
	}
}

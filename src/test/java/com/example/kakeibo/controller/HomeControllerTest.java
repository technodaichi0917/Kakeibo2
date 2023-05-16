package com.example.kakeibo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HomeController.class)
public class HomeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getHomeTest() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("home"));
			
	}
	
	@Test
	public void getLoginTest() throws Exception {
		mockMvc.perform(get("/loginForm"))
		.andExpect(status().isOk())
		.andExpect(view().name("loginForm"));
	}
	
	@Test
	@WithMockUser
	public void getIndexTest() throws Exception {
		mockMvc.perform(get("/index"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));
	}
	
}

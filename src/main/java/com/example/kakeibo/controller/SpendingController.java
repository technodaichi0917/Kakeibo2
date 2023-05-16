package com.example.kakeibo.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.kakeibo.model.Spending;
import com.example.kakeibo.repository.SpendingRepository;
import com.example.kakeibo.service.SpendingService;

@Controller
public class SpendingController {
	
	@Autowired
	private SpendingRepository spendingRepository;
	
	@Autowired
	private SpendingService spendingService;
	
	//新規支出登録ページに遷移
	@GetMapping("/newSpending")
    public String getNewIncome(Model model, Spending spending) {
		LocalDate nowDate = LocalDate.now();
		model.addAttribute("nowDate", nowDate);
        return "newSpending";
    }
	
	//フォームから入力された新規支出登録情報を保存
	@PostMapping("/newSpending")
	public String createSpending(Spending spending) {
		spendingRepository.save(spending);
		return "index2";
	}
	
	//支出情報更新ページに遷移
	@GetMapping("/editSpending/{spendingId}")
	public String editSpending(@PathVariable("spendingId") Spending spending, Model model) {
		model.addAttribute("spending", spending);
		return "editSpending";
	}
	
	//フォームから入力された支出更新情報をspendingServiceへ送る
	@PostMapping("/editSpending")
	public String editSpendingPost(Spending spending) {
		
		spendingService.updateSpending(spending);
		return "redirect:/currentMonthTotal";
	}
}

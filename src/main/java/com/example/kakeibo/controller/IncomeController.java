package com.example.kakeibo.controller;



import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.kakeibo.model.Income;
import com.example.kakeibo.repository.IncomeRepository;
import com.example.kakeibo.service.IncomeService;

@Controller
public class IncomeController {
	
	@Autowired
	private IncomeRepository incomeRepository;
	
	@Autowired
	private IncomeService incomeService;
	
	//新規収入登録ページに遷移
	@GetMapping("/newIncome")
    public String getNewIncome(Model model, Income income) {
		LocalDate nowDate = LocalDate.now();
		model.addAttribute("nowDate", nowDate);
        return "newIncome";
    }
	
	//フォームから入力された新規収入登録情報を保存
	@PostMapping("/newIncome")
	public String createIncome(Income income) {
		incomeRepository.save(income);
		return "index2";
	}
	
	//収入情報更新ページに遷移
	@GetMapping("/editIncome/{incomeId}")
    public String editIncomeGet(@PathVariable("incomeId") Income income, Model model) {
		model.addAttribute("income",income);
		return "editIncome";
    }
	
	//フォームから入力された収入変更情報をincomeServiceへ送る。
	@PostMapping("/editIncome")
	public String editIncomePost(Income income) {
		
		incomeService.updateIncome(income);
		return "redirect:/currentMonthTotal";
	}
	
	
	
	

}

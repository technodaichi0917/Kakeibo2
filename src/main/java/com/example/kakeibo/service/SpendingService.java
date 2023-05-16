package com.example.kakeibo.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kakeibo.model.Income;
import com.example.kakeibo.model.Spending;
import com.example.kakeibo.repository.SpendingRepository;

@Service
public class SpendingService {
	
	@Autowired
	private SpendingRepository spendingRepository;
	
	public List<Spending> getCurrentSpendingList() {
		LocalDate now = LocalDate.now();
		LocalDate start = now.withDayOfMonth(1);
		LocalDate end = now.withDayOfMonth(now.lengthOfMonth());
		List<Spending> spendinglist = spendingRepository.findAllByDatetimeBetween(start,end);
		return spendinglist;
	}
	
	public List<Spending> getSpendingList(int year, int month){
		LocalDate start = LocalDate.of(year, month,1);
		LocalDate end = start.plusMonths(1).minusDays(1);
		return spendingRepository.findAllByDatetimeBetween(start,end);
	}
	

	public void updateSpending(Spending spending) {
		spendingRepository.save(spending);
	}
}
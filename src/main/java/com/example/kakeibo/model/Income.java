package com.example.kakeibo.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class Income {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotNull(message = "適切な金額を入力してください。")
	@Max(999999999)
	@Column(length = 18)
	private Integer incomeAmount;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate datetime;
	
	@NotBlank(message = "適切な内容を入力してください。")
	@Column(length = 18)
	@Size(max =18, message = "文字数は18字以内にしてください。")
	private String memo;
	
	@ManyToOne
    private User user;
	
}
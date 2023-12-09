package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Goods;
import com.example.demo.repository.GoodsRepository;

@Controller
public class MainController {
	@Autowired
	private GoodsRepository goodsR;
	
	@GetMapping("/")
	public String mainpage(Model model) {
		List<Goods> bestgoods = goodsR.findAllOrderByOrderCountDesc();
		List<Goods> limitedList = bestgoods.stream().limit(36).collect(Collectors.toList());
		
		model.addAttribute("bestgoods",limitedList);
		
		List<Goods> newgoods = goodsR.findByOrderByDateDesc();
		List<Goods> limitedList1 = newgoods.stream().limit(36).collect(Collectors.toList());
		
		model.addAttribute("newgoods",limitedList1);
		
		return "main";
	}
}

package com.example.demo.controller;

import java.util.List;

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
		
		model.addAttribute("bestgoods",bestgoods);
		
		List<Goods> newgoods = goodsR.findByOrderByDateDesc();
		
		model.addAttribute("newgoods",newgoods);
		
		return "main";
	}
}

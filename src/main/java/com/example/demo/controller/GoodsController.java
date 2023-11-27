package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoodsController {
	
	@GetMapping("/glist")
	public String goodslist1() {
		return "GoodsList";
	}
	
	@GetMapping("/create")
	public String CreateGoods() {
		return "CreateGoods";
	}
	
	@GetMapping("/goodsview")
	public String GoodsView() {
		return "GoodsView";
	}
	
	@GetMapping("/goodsreview")
	public String GoodsReview() {
		return "GoodsReview";
	}
	
	@GetMapping("/goodsqa")
	public String GoodsQA() {
		return "GoodsQA";
	}
}

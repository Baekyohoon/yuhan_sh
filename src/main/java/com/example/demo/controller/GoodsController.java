package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoodsController {
	
	@GetMapping("/glist_ball")
	public String goodslistball() {
		return "GoodsList_ball";
	}
	
	@GetMapping("/glist_clothes")
	public String goodslistclothes() {
		return "GoodsList_clothes";
	}
	
	@GetMapping("/glist")
	public String goodslist1() {
		return "GoodsList";
	}
	
	@GetMapping("/glist_gloves")
	public String goodslistgloves() {
		return "GoodsList_gloves";
	}
	
	@GetMapping("/glist_shoes")
	public String goodslistshoes() {
		return "GoodsList_shoes";
	}
	
	@GetMapping("/glist_socks")
	public String goodslistsocks() {
		return "GoodsList_socks";
	}
	
	@GetMapping("/glist_uniform")
	public String goodslistuniform() {
		return "GoodsList_uniform";
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

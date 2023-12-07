package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Goods;
import com.example.demo.entity.Review;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.service.GoodsService;

import jakarta.servlet.http.HttpSession;


@Controller
public class ReviewController {
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private GoodsRepository goodsRepository;
	
	@GetMapping("/createreview/{gid}")
	public String CreateReview(@PathVariable int gid, Model model) {
		
		Goods goods = goodsRepository.findByGid(gid);
		
		model.addAttribute("star", 0); // 적절한 초기값으로 설정
	    model.addAttribute("goods", goods);
	    model.addAttribute("gid",gid);
		return "createReview";
		}
	
	@PostMapping("/createreview/{gid}")
	public String CreateReview2(@PathVariable int gid, 
								@RequestParam("comment") String comment,
								@RequestParam("star") int star,
								HttpSession session) {
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		Goods goods = goodsRepository.findByGid(gid);
		
		Review createReview = goodsService.createReview(gid, comment, star, loggedInUserId);
		return "redirect:/goodsreview/{gid}";
		}
	}


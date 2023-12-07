package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	
	@GetMapping("/createreview/{gid}/{oid}")
	public String CreateReview(@PathVariable int gid, @PathVariable int oid,Model model) {
		
		Goods goods = goodsRepository.findByGid(gid);
		
		model.addAttribute("star", 0); // 적절한 초기값으로 설정
	    model.addAttribute("goods", goods);
	    model.addAttribute("gid",gid);
	    model.addAttribute("oid",oid);
		return "createReview";
		}
	
	@PostMapping("/createreview/{gid}/{oid}")
	public String CreateReview2(@PathVariable int gid, 
								@PathVariable int oid, 
								@RequestParam("comment") String comment,
								@RequestParam("star") int star,
								@RequestParam(name = "file") MultipartFile file,
								HttpSession session) {
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		Goods goods = goodsRepository.findByGid(gid);
		
		Review createReview = goodsService.createReview(gid, oid, comment, star, loggedInUserId, file);
		
		
		return "redirect:/goodsreview/{gid}";
		}
	}


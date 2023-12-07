package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Goods;
import com.example.demo.entity.QA;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.repository.QARepository;
import com.example.demo.service.GoodsService;

import jakarta.servlet.http.HttpSession;

@Controller
public class QAController {
	
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private QARepository qaRepository;
	@Autowired
	private GoodsRepository goodsRepository;
	
	
	@GetMapping("/createqa/{gid}")
	public String CreateQA(@PathVariable int gid, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		
		if(loggedInUserId == null) {
			redirectAttributes.addFlashAttribute("loginMessage", "로그인 상태가 아닙니다!"); 
			return "redirect:/login";
		}else {
		Goods goods = goodsRepository.findByGid(gid);
		
	    model.addAttribute("goods", goods);
	    model.addAttribute("gid",gid);
		return "createqa";
		}
	}
	
	@PostMapping("/createqa/{gid}")
	public String CreateQA2(@PathVariable int gid,
							@RequestParam("comment") String comment, 
							@RequestParam("visibility") String visibility,
							HttpSession session) {
		 String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		QA createdQA = goodsService.createQA(gid, comment, visibility, loggedInUserId);
		return "redirect:/goodsqa/{gid}";
	}
	
	@PostMapping("/createanswer/{qid}/{gid}")
	public String CreateAnswer(@PathVariable int qid,
								@PathVariable int gid,
							   @RequestParam(name = "answer") String answer) {
		 QA qa = qaRepository.findByQid(qid);
		 qa.setAnswer(answer);
		 qaRepository.save(qa);
		
		return "redirect:/goodsqa/"+gid;
	}
}

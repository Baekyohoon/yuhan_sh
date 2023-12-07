package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.QA;
import com.example.demo.entity.Review;
import com.example.demo.entity.User;
import com.example.demo.repository.QARepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyPageController {
	@Autowired 
	private UserRepository userR;
	@Autowired
	private ReviewRepository reviewR;
	@Autowired
	private QARepository qaR;
	
	@GetMapping("/edituser")
	public String edituser(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		
		if(loggedInUserId == null) {
			redirectAttributes.addFlashAttribute("loginMessage", "로그인 상태가 아닙니다!"); 
			return "redirect:/login";
		}else {
			User user = userR.findByUserId(loggedInUserId);
			model.addAttribute("user",user);
			return "editUser";
		}	
	}
	
	@PostMapping("/edituser")
	public String changepw(@RequestParam(name="passwd") String passwd, @RequestParam(name="confirmpasswd") String confirmpasswd, HttpSession session,
			RedirectAttributes redirectAttributes) {
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		User user = userR.findByUserId(loggedInUserId);
		if(passwd.equals(confirmpasswd)) {
			user.setPasswd(passwd);
			userR.save(user);
			redirectAttributes.addFlashAttribute("successMessage", "비밀번호가 변경되었습니다."); 
			return "redirect:/edituser";
		}else {
			redirectAttributes.addFlashAttribute("failedMessage", "비밀번호가 일치하지 않습니다."); 
			return "redirect:/edituser";
		}
		
		
	}
	
	
	
	@GetMapping("/myqa")
	public String myqa(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		User user = userR.findByUserId(loggedInUserId);
		
		if(loggedInUserId == null) {
			redirectAttributes.addFlashAttribute("loginMessage", "로그인 상태가 아닙니다!"); 
			return "redirect:/login";
		}else if(user.getId().equals("admin")) {
			List<QA> qas = qaR.findByOrderByQid();
			model.addAttribute("qas",qas);
			return "mypage_Q";
		}
		else {
			List<QA> qas = qaR.findByUserOrderByQid(user);
			model.addAttribute("qas",qas);
			return "mypage_Q";
		}	
		
	}
	
	
	@GetMapping("/myreview")
	public String MyReview(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		User user = userR.findByUserId(loggedInUserId);
		
		if(loggedInUserId == null) {
			redirectAttributes.addFlashAttribute("loginMessage", "로그인 상태가 아닙니다!"); 
			return "redirect:/login";
		}else if(user.getId().equals("admin")) {
			List<Review> reviews = reviewR.findByOrderByDate();
			model.addAttribute("reviews",reviews);
			return "mypage_R";
		}
		else {
			List<Review> reviews = reviewR.findByUserOrderByDate(user);
			model.addAttribute("reviews",reviews);
			
			return "mypage_R";
		}	
		
	}
	
	
}

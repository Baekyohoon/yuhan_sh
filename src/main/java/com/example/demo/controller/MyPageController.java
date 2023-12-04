package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {

	@GetMapping("/edituser")
	public String edituser() {
		return "editUser";
	}
	
	@GetMapping("/orders")
	public String oders() {
		return "Orders";
	}
	
	@GetMapping("/myqa")
	public String myqa() {
		return "MyQA";
	}
	
	
	@GetMapping("/myreview")
	public String MyReview() {
		return "MyReview";
	}
	
	
}

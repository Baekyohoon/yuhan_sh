package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String Login() {
		return "Login";
	}
	@GetMapping("/signup1")
	public String signup1() {
		return "signup1";
	}
	@GetMapping("/signup2")
	public String signup2() {
		return "signup2";
	}
	@GetMapping("/ipsearch")
	public String ipsearch() {
		return "ipsearch";
	}
}

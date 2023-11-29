package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoxController {
	@GetMapping("/mybox")
	public String mybox() {
		return "Box";
	}
}

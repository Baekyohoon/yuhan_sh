package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Goods;
import com.example.demo.entity.Orders;
import com.example.demo.entity.User;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoxController {
	
	@Autowired
	private GoodsRepository goodsR;
	@Autowired
	private UserRepository userR;
	@Autowired
	private OrdersRepository ordersR;
	
	@GetMapping("/mybox")
	public String mybox(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		User user = userR.findByUserId(loggedInUserId); 
		
		if(loggedInUserId == null) {
			redirectAttributes.addFlashAttribute("loginMessage", "로그인 상태가 아닙니다!"); 
			return "redirect:/login";
		}else {
			List<Orders> orders = ordersR.findByUserAndState(user, "장바구니");
			model.addAttribute("orders",orders);
			return "Box";
		}
		
	}
	
	@PostMapping("/createbox")
	public String createbox(@RequestParam String boxsize, @RequestParam int boxcount,
			HttpSession session, RedirectAttributes redirectAttributes,
			@RequestParam int gid) {
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		User user = userR.findByUserId(loggedInUserId);
		
		if(loggedInUserId == null) {
			redirectAttributes.addFlashAttribute("loginMessage", "로그인 상태가 아닙니다!"); 
			return "redirect:/login";
		}else {
			Goods goods= goodsR.findByGid(gid);
			Orders oders = new Orders();
			oders.setCount(boxcount);
			oders.setGoods(goods);
			oders.setState("장바구니");
			oders.setUser(user);
			oders.setPrice(goods.getPrice()*boxcount);
			ordersR.save(oders);
			return "redirect:/mybox";
		}
	}
}

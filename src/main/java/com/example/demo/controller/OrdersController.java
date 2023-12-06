package com.example.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class OrdersController {
	@Autowired
	private GoodsRepository goodsR;
	@Autowired
	private UserRepository userR;
	@Autowired
	private OrdersRepository ordersR;
	
	@GetMapping("/pay/{oid}")
	public String payview(@PathVariable int oid, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		User user = userR.findByUserId(loggedInUserId);
		Orders order1 = ordersR.findById(oid);
		int gid = order1.getGoods().getGid();
		Goods goods= goodsR.findByGid(gid);
		if(loggedInUserId == null) {
			redirectAttributes.addFlashAttribute("loginMessage", "로그인 상태가 아닙니다!"); 
			return "redirect:/login";
		}else {
			//Orders orders = ordersR.findByGoodsAndUserAndState(goods, user, "주문 대기");
			model.addAttribute("user",user);
			model.addAttribute("orders",order1);
			return "pay";
		}
		
	}
	@PostMapping("/createorders")
	public String createorders(@RequestParam String size, @RequestParam int count,
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
			oders.setCount(count);
			oders.setGoods(goods);
			oders.setState("주문 대기");
			oders.setUser(user);
			oders.setPrice(goods.getPrice()*count);
			ordersR.save(oders);
			return "redirect:/pay/"+oders.getOid();
		}
	}
	
	@PostMapping("/completeorders")
	public String completeorders(@RequestParam int oid,RedirectAttributes redirectAttributes) {
		Orders orders = ordersR.findById(oid);
		orders.setState("결제 완료");
		ordersR.save(orders);
		redirectAttributes.addFlashAttribute("successMessage", "결제 완료!");
		return "redirect:/";
	}
	
	@PostMapping("/deleteorders")
	public String deleteorders(@RequestParam int oid,RedirectAttributes redirectAttributes) {
		Orders orders = ordersR.findById(oid);
		ordersR.delete(orders);
		redirectAttributes.addFlashAttribute("successMessage", "주문취소!");
		return "redirect:/";
	}
}

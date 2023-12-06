package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class OrdersController {
	@Autowired
	private GoodsRepository goodsR;
	@Autowired
	private UserRepository userR;
	@Autowired
	private OrdersRepository ordersR;
	
	@GetMapping("/pay")
	public String payview(HttpServletRequest request, Model model,
	        HttpSession session, RedirectAttributes redirectAttributes) {
	    String loggedInUserId = (String) session.getAttribute("loggedInUserId");
	    User user = userR.findByUserId(loggedInUserId);

	    // HttpServletRequest를 통해 직접 oid 파라미터를 받기
	    String[] oidArray = request.getParameterValues("oids");

	    // 쉼표로 구분된 문자열을 각각의 정수로 변환
	    List<Integer> oids = Arrays.stream(oidArray)
	            .flatMap(s -> Arrays.stream(s.split(",")))
	            .map(Integer::parseInt)
	            .collect(Collectors.toList());

	    if (loggedInUserId == null) {
	        redirectAttributes.addFlashAttribute("loginMessage", "로그인 상태가 아닙니다!");
	        return "redirect:/login";
	    } else {
	        List<Orders> order1 = ordersR.findByOid(oids);
	        int sumprice = 0;
	        for (int i = 0; i < order1.size(); i++) {
				sumprice += order1.get(i).getPrice();
			}
	        model.addAttribute("user", user);
	        model.addAttribute("orders", order1);
	        model.addAttribute("sumprice", sumprice);
	        return "pay";
	    }
	}

	@PostMapping("/createorders")
	public String createorders(@RequestParam(name="size", required = false) List<String> size, @RequestParam List<Integer> count,
			HttpSession session, RedirectAttributes redirectAttributes,
			@RequestParam int gid) {
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		User user = userR.findByUserId(loggedInUserId);
		
		if(loggedInUserId == null) {
			redirectAttributes.addFlashAttribute("loginMessage", "로그인 상태가 아닙니다!"); 
			return "redirect:/login";
		}else {
			Goods goods= goodsR.findByGid(gid);
			List<Integer> oids = new ArrayList<>();
			for (int i = 0; i < size.size(); i++) {
				Orders oders = new Orders();
				oders.setCount(count.get(i));
				oders.setGoods(goods);
				oders.setSize(size.get(i));
				oders.setState("주문 대기");
				oders.setUser(user);
				oders.setPrice(goods.getPrice()*count.get(i));
				ordersR.save(oders);
				oids.add(oders.getOid());
			}
			
			// pay 컨트롤러로 oid 리스트를 그대로 전달
	        redirectAttributes.addAttribute("oids", oids);
	        return "redirect:/pay";
			
		}
	}
	
	@PostMapping("/completeorders")
	public String completeorders(@RequestParam(name="do") List<Integer> orders,RedirectAttributes redirectAttributes) {
		for (int i = 0; i < orders.size(); i++) {
			Orders oders = ordersR.findById((int)orders.get(i));
			oders.setState("결제 완료");
			ordersR.save(oders);
		}
		
		redirectAttributes.addFlashAttribute("successMessage", "결제 완료!");
		return "redirect:/";
	}
	
	@PostMapping("/deleteorders")
	public String deleteorders(@RequestParam(name="do") List<Integer> orders,RedirectAttributes redirectAttributes) {
		for (int i = 0; i < orders.size(); i++) {
			Orders oders = ordersR.findById((int)orders.get(i));
			ordersR.delete(oders);
		}
		redirectAttributes.addFlashAttribute("successMessage", "주문취소!");
		return "redirect:/";
	}
}

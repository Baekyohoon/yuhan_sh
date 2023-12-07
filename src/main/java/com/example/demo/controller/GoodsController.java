package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Goods;
import com.example.demo.entity.GoodsInfo;
import com.example.demo.entity.Images;
import com.example.demo.entity.Inventory;
import com.example.demo.entity.QA;
import com.example.demo.repository.GoodsInfoRepository;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.repository.ImagesRepository;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.QARepository;
import com.example.demo.service.GoodsService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GoodsController {
	
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private GoodsRepository goodsRepository;
	@Autowired
	private InventoryRepository inventoryRepository;
	@Autowired
	private GoodsInfoRepository goodsInfoRepository;
	@Autowired
	private ImagesRepository imagesRepository;
	@Autowired
	private QARepository qaRepository;
	
	@GetMapping("/glist_ball")
	public String goodslistball() {
		return "GoodsList_ball";
	}
	
	@GetMapping("/glist_clothes")
	public String goodslistclothes() {
		return "GoodsList_clothes";
	}
	
	@GetMapping("/glist")
	public String goodslist1() {
		return "GoodsList";
	}
	
	@GetMapping("/glist_gloves")
	public String goodslistgloves() {
		return "GoodsList_gloves";
	}
	
	@GetMapping("/glist_shoes")
	public String goodslistshoes() {
		return "GoodsList_shoes";
	}
	
	@GetMapping("/glist_socks")
	public String goodslistsocks() {
		return "GoodsList_socks";
	}
	
	@GetMapping("/glist_uniform")
	public String goodslistuniform() {
		return "GoodsList_uniform";
	}
	
	@GetMapping("/create")
	public String CreateGoods() {
		return "CreateGoods";
	}
	
	@PostMapping("/creategoods")
	public String CreateGoods2( @RequestParam("productName") String productName,
					            @RequestParam("productPrice") int productPrice, 
					            @RequestParam("productDescription") String productDescription, 
					            @RequestParam("category") String category,
					            @RequestParam(name="file") List<MultipartFile> file,
					            @RequestParam(name="size") List<String> size,
					            @RequestParam(name="count") List<Integer> count) {
		// Goods 생성
        Goods createdGoods = goodsService.createGoods(productName, productPrice, productDescription, category, file, size, count);
		return "redirect:/";
	}
	
	
	@GetMapping("/goodsview/{gid}")
	public String GoodsView(@PathVariable int gid, Model model) {
		Goods goods = goodsRepository.findByGid(gid);
		GoodsInfo goodsInfo = goodsInfoRepository.findByGoods(goods);
		List<Inventory> inventories = inventoryRepository.findByGoods(goods);
		// Goods 객체를 모델에 추가합니다.
	    model.addAttribute("goods", goods);
	    model.addAttribute("goodsInfo", goodsInfo);
	    model.addAttribute("inventories", inventories);
	    model.addAttribute("gid",gid);
		return "GoodsView";
	}
	
	@GetMapping("/goodsreview/{gid}")
	public String GoodsReview(@PathVariable int gid, Model model) {
		Goods goods = goodsRepository.findByGid(gid);
		GoodsInfo goodsInfo = goodsInfoRepository.findByGoods(goods);
		List<Inventory> inventories = inventoryRepository.findByGoods(goods);
		// Goods 객체를 모델에 추가합니다.
	    model.addAttribute("goods", goods);
	    model.addAttribute("goodsInfo", goodsInfo);
	    model.addAttribute("inventories", inventories);
	    model.addAttribute("gid",gid);
		return "GoodsReview";
	}
	
	@GetMapping("/goodsqa/{gid}")
	public String GoodsQA(@PathVariable int gid, Model model) {
		Goods goods = goodsRepository.findByGid(gid);
		GoodsInfo goodsInfo = goodsInfoRepository.findByGoods(goods);
		List<Inventory> inventories = inventoryRepository.findByGoods(goods);
		// Goods 객체를 모델에 추가합니다.
	    model.addAttribute("goods", goods);
	    model.addAttribute("goodsInfo", goodsInfo);
	    model.addAttribute("inventories", inventories);
	    model.addAttribute("gid",gid);
	    List<QA> qaList;
	    qaList = qaRepository.findAll();
	    model.addAttribute("qaList", qaList);
		return "GoodsQA";
	}
	
	
	
	
}

package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Category;
import com.example.demo.entity.Goods;
import com.example.demo.entity.GoodsInfo;
import com.example.demo.entity.Inventory;
import com.example.demo.entity.QA;
import com.example.demo.entity.Review;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.GoodsInfoRepository;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.repository.ImagesRepository;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.QARepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.service.GoodsService;

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
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("/glist_ball")
	public String goodslistball(Model model, @RequestParam(name = "sort", required = false) String sort) {
		if("latest".equals(sort)) {
			String name = "축구공";
			List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByDateDesc(name);
			model.addAttribute("goods", goods);
			
			return "GoodsList_ball";
		}else if("star".equals(sort)){
			 String name = "축구공";
			    List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByReviewStarDesc(name);
			    model.addAttribute("goods", goods);
			    
			    return "GoodsList_ball";
		}else if("high".equals(sort)){
			String name = "축구공";
			List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByPriceDesc(name);
			model.addAttribute("goods", goods);
			
			return "GoodsList_ball";
		}else if("low".equals(sort)){
			String name = "축구공";
			List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByPrice(name);
			model.addAttribute("goods", goods);
			
			return "GoodsList_ball";
		}
		else {
			String name = "축구공";
			List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByDateDesc(name);
			model.addAttribute("goods", goods);

			return "GoodsList_ball";
		}
	}
	
	@GetMapping("/glist_clothes")
	public String goodslistclothes(Model model, @RequestParam(name = "sort", required = false) String sort) {
		if("latest".equals(sort)) {
			String name = "의류";
			List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByDateDesc(name);
			model.addAttribute("goods", goods);
			
			return "GoodsList_clothes";
		}else if("star".equals(sort)){
			 String name = "의류";
			    List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByReviewStarDesc(name);
			    model.addAttribute("goods", goods);
			    
			    return "GoodsList_clothes";
		}else if("high".equals(sort)){
			String name = "의류";
			List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByPriceDesc(name);
			model.addAttribute("goods", goods);
			
			return "GoodsList_clothes";
		}else if("low".equals(sort)){
			String name = "의류";
			List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByPrice(name);
			model.addAttribute("goods", goods);
			
			return "GoodsList_clothes";
		}
		else {
			String name = "의류";
			List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByDateDesc(name);
			model.addAttribute("goods", goods);

			return "GoodsList_clothes";
		}
	}
	
	@GetMapping("/glist")
	public String goodslist1() {
		return "GoodsList";
	}
	
	@GetMapping("/glist_gloves")
	public String goodslistgloves(Model model, @RequestParam(name = "sort", required = false) String sort) {
		if("latest".equals(sort)) {
			String name = "골키퍼장갑";
			List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByDateDesc(name);
			model.addAttribute("goods", goods);
			
			return "GoodsList_gloves";
		}else if("star".equals(sort)){
			 String name = "골키퍼장갑";
			    List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByReviewStarDesc(name);
			    model.addAttribute("goods", goods);
			    
			    return "GoodsList_gloves";
		}else if("high".equals(sort)){
			String name = "골키퍼장갑";
			List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByPriceDesc(name);
			model.addAttribute("goods", goods);
			
			return "GoodsList_gloves";
		}else if("low".equals(sort)){
			String name = "골키퍼장갑";
			List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByPrice(name);
			model.addAttribute("goods", goods);
			
			return "GoodsList_gloves";
		}
		else {
			String name = "골키퍼장갑";
			List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByDateDesc(name);
			model.addAttribute("goods", goods);

			return "GoodsList_gloves";
		}
	}
	
	@GetMapping("/glist_shoes")
	public String goodslistshoes(Model model, @RequestParam(name = "sort", required = false) String sort) {
		if("latest".equals(sort)) {
			String name = "축구화";
			List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByDateDesc(name);
			model.addAttribute("goods", goods);
			
			return "GoodsList_shoes";
		}else if("star".equals(sort)){
			 String name = "축구화";
			    List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByReviewStarDesc(name);
			    model.addAttribute("goods", goods);
			    
			    return "GoodsList_shoes";
		}else if("high".equals(sort)){
			String name = "축구화";
			List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByPriceDesc(name);
			model.addAttribute("goods", goods);
			
			return "GoodsList_shoes";
		}else if("low".equals(sort)){
			String name = "축구화";
			List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByPrice(name);
			model.addAttribute("goods", goods);
			
			return "GoodsList_shoes";
		}
		else {
			String name = "축구화";
			List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByDateDesc(name);
			model.addAttribute("goods", goods);

			return "GoodsList_shoes";
		}
		
	}
	
	@GetMapping("/glist_socks")
	public String goodslistsocks(Model model, @RequestParam(name = "sort", required = false) String sort) {
		if("latest".equals(sort)) {
			String name = "양말";
			List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByDateDesc(name);
			model.addAttribute("goods", goods);
			
			return "GoodsList_socks";
		}else if("star".equals(sort)){
			 String name = "양말";
			    List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByReviewStarDesc(name);
			    model.addAttribute("goods", goods);
			    
			    return "GoodsList_socks";
		}else if("high".equals(sort)){
			String name = "양말";
			List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByPriceDesc(name);
			model.addAttribute("goods", goods);
			
			return "GoodsList_socks";
		}else if("low".equals(sort)){
			String name = "양말";
			List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByPrice(name);
			model.addAttribute("goods", goods);
			
			return "GoodsList_socks";
		}
		else {
			String name = "양말";
			List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByDateDesc(name);
			model.addAttribute("goods", goods);

			return "GoodsList_socks";
		}
	}
	
	@GetMapping("/glist_uniform")
	public String goodslistuniform(Model model, @RequestParam(name = "sort", required = false) String sort) {
		if("latest".equals(sort)) {
			String name = "유니폼";
			List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByDateDesc(name);
			model.addAttribute("goods", goods);
			
			return "GoodsList_uniform";
		}else if("star".equals(sort)){
			 String name = "유니폼";
			    List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByReviewStarDesc(name);
			    model.addAttribute("goods", goods);
			    
			    return "GoodsList_uniform";
		}else if("high".equals(sort)){
			String name = "유니폼";
			List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByPriceDesc(name);
			model.addAttribute("goods", goods);
			
			return "GoodsList_uniform";
		}else if("low".equals(sort)){
			String name = "유니폼";
			List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByPrice(name);
			model.addAttribute("goods", goods);
			
			return "GoodsList_uniform";
		}
		else {
			String name = "유니폼";
			List<Goods> goods = goodsRepository.findByCategory_CategorynameOrderByDateDesc(name);
			model.addAttribute("goods", goods);

			return "GoodsList_uniform";
		}
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
	    List<Review> reviewList;
	    reviewList = reviewRepository.findAll();
	 // 내림차순으로 정렬
	    Collections.sort(reviewList, (r1, r2) -> Long.compare(r2.getRid(), r1.getRid()));
	    model.addAttribute("reviewList", reviewList);
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
	    Collections.sort(qaList, (r1, r2) -> Long.compare(r2.getQid(), r1.getQid()));
	    model.addAttribute("qaList", qaList);
		return "GoodsQA";
	}
	
	@GetMapping("/search")
	public String searchGoods(Model model,
							  @RequestParam(required = false) String keyword,
							  @RequestParam(name = "sort", required = false) String sort) {
		
		if("latest".equals(sort)) {
		List<Goods> goods = goodsRepository.findByGnameContainingIgnoreCaseOrderByDateDesc(keyword);
		model.addAttribute("goods" ,goods);
		model.addAttribute("keyword" ,keyword);
		return "search";
		}else if("star".equals(sort)){
			List<Goods> goods = goodsRepository.findByGnameContainingIgnoreCaseOrderByReviewStarDesc(keyword);
			model.addAttribute("goods" ,goods);
			model.addAttribute("keyword" ,keyword);
			return "search";
		}else if("high".equals(sort)) {
			List<Goods> goods = goodsRepository.findByGnameContainingIgnoreCaseOrderByPriceDesc(keyword);
			model.addAttribute("goods" ,goods);
			model.addAttribute("keyword" ,keyword);
			return "search";
		}else if("low".equals(sort)) {
			List<Goods> goods = goodsRepository.findByGnameContainingIgnoreCaseOrderByPrice(keyword);
			model.addAttribute("goods" ,goods);
			model.addAttribute("keyword" ,keyword);
			return "search";
		}else {
			List<Goods> goods = goodsRepository.findByGnameContainingIgnoreCaseOrderByDateDesc(keyword);
			model.addAttribute("goods" ,goods);
			model.addAttribute("keyword" ,keyword);
			return "search";
		}
		
	}
	
	
}

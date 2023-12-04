package com.example.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Goods;
import com.example.demo.entity.GoodsInfo;
import com.example.demo.entity.Inventory;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.GoodsInfoRepository;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.repository.InventoryRepository;

import jakarta.transaction.Transactional;

@Service
public class GoodsService {
	
	@Autowired
	private GoodsRepository goodsRepository;
	
	@Autowired
    private GoodsInfoRepository goodsInfoRepository;
	
	@Autowired
    private CategoryRepository categoryRepository;
	
	 @Autowired
	 private InventoryRepository inventoryRepository;
	
	 @Transactional
	public Goods createGoods(String productName, int productPrice, String productDescription, String categoryName, String size, int count) {
	    // GoodsInfo 생성 및 저장
	    GoodsInfo goodsInfo = new GoodsInfo();
	    goodsInfo.setContent(productDescription);
	    goodsInfoRepository.save(goodsInfo);

	    // Goods 이름, 가격, 날짜 설정
	    Goods goods = new Goods();
	    goods.setGname(productName);
	    goods.setPrice(productPrice);
	    goods.setDate(new Date());
	    goods.setGoodsInfo(goodsInfo);
	    
	 // Goos 카테고리 설정
	    Category category = new Category();
	    category.setCategoryname(categoryName);
	    category.setGoods(goods);
	    categoryRepository.save(category);

        // Goods와 Category 연결
        goods.setCategory(category);
	    // Goods 저장
	    goodsRepository.save(goods);

	    // GoodsInfo에 Goods의 gid 설정
	    goodsInfo.setGoods(goods);
	    goodsInfoRepository.save(goodsInfo);
	    
	 // Size 및 Count 정보 저장
        Inventory inventory = new Inventory();
        inventory.setSize(size);
        inventory.setCount(count);
        inventory.setGoods(goods);
        inventoryRepository.save(inventory);

	    // Goods 엔티티 반환
	    return goods;
	}
	
}

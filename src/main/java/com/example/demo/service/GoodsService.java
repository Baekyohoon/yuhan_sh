package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Goods;
import com.example.demo.entity.GoodsInfo;
import com.example.demo.entity.Images;
import com.example.demo.entity.Size;
import com.example.demo.repository.GoodsRepository;

@Service
public class GoodsService {
	
	@Autowired
	private GoodsRepository goodsRepository;
	
	public void saveGoods(String productName, int productPrice, String productDescription,
                          String category, String mainImagePath, List<String> additionalImagePaths, List<String> sizes) {
		Goods goods = new Goods();
		goods.setGname(productName);
		goods.setPrice(productPrice);
		goods.setDate(new Date()); // 현재 날짜로 설정

		// 카테고리 설정
		Category categoryEntity = new Category();
		categoryEntity.setCategoryname(category);
		categoryEntity.setGoods(goods);
		goods.setCategory(categoryEntity);

		// 상세정보 설정
		GoodsInfo goodsInfo = new GoodsInfo();
		goodsInfo.setContent(productDescription);
		goodsInfo.setGoods(goods);
		goods.setGoodsInfo(goodsInfo);

		// 메인 이미지 설정
		goods.setMainp(mainImagePath);

		// 추가 이미지 설정
		for (String imagePath : additionalImagePaths) {
			Images image = new Images();
			image.setPhotopath(imagePath);
			image.setGoods(goods);
			goods.getImages().add(image);
		}

		// 사이즈 설정
		for (String size : sizes) {
			Size sizeEntity = new Size();
			sizeEntity.setSize(size);
			sizeEntity.setGoods(goods);
			goods.getSize().add(sizeEntity);
		}

		// 저장
		goodsRepository.save(goods);
	}
}

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Goods;
import com.example.demo.entity.GoodsInfo;

public interface GoodsInfoRepository extends JpaRepository<GoodsInfo, Integer>{
	GoodsInfo findByGoods(Goods goods);
}

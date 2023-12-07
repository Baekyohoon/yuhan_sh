package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Category;
import com.example.demo.entity.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Integer>{
	Goods findByGid(int gid);
	List<Goods> findByCategory_Categoryname(String categoryname);
}

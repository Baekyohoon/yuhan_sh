package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Goods;
import com.example.demo.entity.Orders;
import com.example.demo.entity.User;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{
	Orders findByGoodsAndUserAndState(Goods goods, User user, String  state);
	
	Orders findById(int id);
}

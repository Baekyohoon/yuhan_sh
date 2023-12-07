package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Goods;
import com.example.demo.entity.Orders;
import com.example.demo.entity.User;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{
	Orders findByGoodsAndUserAndState(Goods goods, User user, String  state);
	
	Orders findById(int id);
	
	List<Orders> findByUserAndState(User user, String  state);
	
	List<Orders> findByUserAndStateIn(User user, List<String> state);
	
	List<Orders> findByStateOrState(String  state, String  state1);
	
	List<Orders> findByUser(User user);
	
	
	
	@Query(value = "SELECT * FROM orders WHERE oid IN (:oid)", nativeQuery = true)
	List<Orders> findByOid(@Param("oid") List<Integer> oid);
	
	@Query(value = "SELECT * FROM orders WHERE oid IN (:oid)", nativeQuery = true)
	Orders findByOid1(@Param("oid") int oid);

}

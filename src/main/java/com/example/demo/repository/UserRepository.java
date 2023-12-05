package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query(value = "SELECT * FROM user WHERE id = :id ", nativeQuery = true)
	List<User> findByUserIdList(String id);
	
	@Query(value = "SELECT * FROM user WHERE id = :id ", nativeQuery = true)
	User findByUserId(String id);
	
	@Query(value = "SELECT * FROM user WHERE email = :email ", nativeQuery = true)
	List<User> findByemailList(String email);
	
	@Query(value = "SELECT * FROM user WHERE email = :email ", nativeQuery = true)
	User findByemail(String email);
}

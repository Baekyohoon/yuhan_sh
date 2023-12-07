package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.QA;
import com.example.demo.entity.Review;
import com.example.demo.entity.User;

public interface QARepository extends JpaRepository<QA, Integer>{
	List<QA> findByUserOrderByQid(User user);
	List<QA> findByOrderByQid();
	QA findByQid(int qid);
}

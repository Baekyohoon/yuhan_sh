package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.QA;

public interface QARepository extends JpaRepository<QA, Integer>{
	QA findByQid(int qid);
}

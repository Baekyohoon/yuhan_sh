package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Box;

public interface BoxRepository extends JpaRepository<Box, Integer>{

}

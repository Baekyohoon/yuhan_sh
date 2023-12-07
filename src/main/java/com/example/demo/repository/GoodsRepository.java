package com.example.demo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.demo.entity.Category;
import com.example.demo.entity.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Integer>{
	Goods findByGid(int gid);
	List<Goods> findByCategory(Category category);
	List<Goods> findByCategory_Categoryname(String categoryname);
	List<Goods> findByCategory_CategorynameOrderByDateDesc(String categoryname);
	List<Goods> findByCategory_CategorynameOrderByPriceDesc(String categoryname);
	List<Goods> findByCategory_CategorynameOrderByPrice(String categoryname);
	
	@Query("SELECT g, AVG(COALESCE(r.star, 0)) as avgstar " +
	           "FROM Goods g " +
	           "LEFT JOIN g.review r " +
	           "JOIN g.category c " +
	           "WHERE c.categoryname = :categoryname " +
	           "GROUP BY g " +
	           "ORDER BY avgstar DESC")
	List<Goods> findByCategory_CategorynameOrderByReviewStarDesc(String categoryname);
	
	List<Goods> findByGnameContainingIgnoreCase(String keyword);
	List<Goods> findByGnameContainingIgnoreCaseOrderByDateDesc(String keyword);
	List<Goods> findByGnameContainingIgnoreCaseOrderByPrice(String keyword);
	List<Goods> findByGnameContainingIgnoreCaseOrderByPriceDesc(String keyword);

	
	@Query("SELECT g, AVG(COALESCE(r.star, 0)) as avgstar " +
		       "FROM Goods g " +
		       "LEFT JOIN g.review r " +
		       "WHERE lower(g.gname) LIKE lower(concat('%', :keyword, '%')) " +
		       "GROUP BY g " +
		       "ORDER BY avgstar DESC")
		List<Goods> findByGnameContainingIgnoreCaseOrderByReviewStarDesc(@Param("keyword") String keyword);

}


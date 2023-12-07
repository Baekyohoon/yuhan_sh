package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Category;
import com.example.demo.entity.Goods;
import com.example.demo.entity.GoodsInfo;
import com.example.demo.entity.Images;
import com.example.demo.entity.Inventory;
import com.example.demo.entity.Orders;
import com.example.demo.entity.QA;
import com.example.demo.entity.Review;
import com.example.demo.entity.User;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.GoodsInfoRepository;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.repository.ImagesRepository;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.repository.QARepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class GoodsService {
	
	@Autowired
	private GoodsRepository goodsRepository;
	
	@Autowired
    private GoodsInfoRepository goodsInfoRepository;
	
	@Autowired
    private CategoryRepository categoryRepository;
	
	 @Autowired
	 private InventoryRepository inventoryRepository;
	 
	 @Autowired
	 private ImagesRepository imagesRepository;
	 
	 @Autowired
	 private QARepository qaRepository;
	 
	 @Autowired
	 private UserRepository userRepository;
	 
	 @Autowired
	 private ReviewRepository reviewRepository;
	 
	 @Autowired
	 private OrdersRepository ordersRepository;
	// 이미지를 저장할 경로
	 @Value("${multipart.file-upload.location}")
	    private String uploadPath;
	
	 @Transactional
	public Goods createGoods(String productName, int productPrice, String productDescription, String categoryName, List<MultipartFile> file, List<String> size, List<Integer> count) {
		// 이미지 저장
	    List<String> fileName = saveImage(file);
	        
	    // GoodsInfo 생성 및 저장
	    GoodsInfo goodsInfo = new GoodsInfo();
	    goodsInfo.setContent(productDescription);
	    goodsInfoRepository.save(goodsInfo);

	    // Goods 이름, 가격, 날짜 설정
	    Goods goods = new Goods();
	    goods.setGname(productName);
	    goods.setPrice(productPrice);
	    goods.setDate(new Date());
	    goods.setGoodsInfo(goodsInfo);
	    
	 // 이미지 경로 저장
	    if(fileName.size() == 1) {
	    
        goods.setMainp(fileName.get(0));
	    }else {
	    	for (int i = 1; i < fileName.size(); i++) {
				Images images = new Images();
				images.setPhotopath(fileName.get(i));
				images.setGoods(goods);
				imagesRepository.save(images);
			}
	    	goods.setMainp(fileName.get(0));
	    }
	    
	    
	 // Goos 카테고리 설정
	    Category category = new Category();
	    category.setCategoryname(categoryName);
	    category.setGoods(goods);
	    categoryRepository.save(category);

        // Goods와 Category 연결
        goods.setCategory(category);
	    // Goods 저장
	    goodsRepository.save(goods);

	    // GoodsInfo에 Goods의 gid 설정
	    goodsInfo.setGoods(goods);
	    goodsInfoRepository.save(goodsInfo);
	    
		for (int i = 0; i < size.size(); i++) {
			 // Size 및 Count 정보 저장
		     Inventory inventory = new Inventory();
			 inventory.setSize(size.get(i)); inventory.setCount(count.get(i));
			 inventory.setGoods(goods); inventoryRepository.save(inventory);
		}
	    

	    // Goods 엔티티 반환
	    return goods;
	}
	
	 private List<String> saveImage(List<MultipartFile> file) {
	        try {
	        	String uploadDir = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img"; // 실제 디렉토리 경로로 설정해야 합니다.
	        	List<String> files = new ArrayList<>();
	        	for (int i = 0; i < file.size(); i++) {
	        		// 업로드 디렉토리 생성
		            Path uploadPath = Paths.get(this.uploadPath);
		            if (!Files.exists(uploadPath)) {
		                Files.createDirectories(uploadPath);
		            }

		            // 파일명 중복을 피하기 위해 현재 시간을 이용한 고유한 파일명 생성
		            String fileName = System.currentTimeMillis() + "_" + file.get(i).getOriginalFilename();

		            // 파일 저장 경로
		            Path filePath = uploadPath.resolve(fileName);
		            String filePath1 = Paths.get(uploadDir, fileName).toString();
		            // 파일 저장
		            Files.copy(file.get(i).getInputStream(), Paths.get(filePath1), StandardCopyOption.REPLACE_EXISTING);
		            files.add(fileName);
				}
	            

	            return files;
	        } catch (IOException e) {
	            // 파일 저장 중 오류 발생 시 예외 처리
	            throw new RuntimeException("Failed to save image", e);
	        }
	    }
	 
	 private String saveReviewImage(MultipartFile file) {
		    try {
		        String uploadDir = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img"; // 실제 디렉토리 경로로 설정해야 합니다.

		        // 업로드 디렉토리 생성
		        Path uploadPath = Paths.get(uploadDir);
		        if (!Files.exists(uploadPath)) {
		            Files.createDirectories(uploadPath);
		        }

		        // 파일명 중복을 피하기 위해 현재 시간을 이용한 고유한 파일명 생성
		        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

		        // 파일 저장 경로
		        Path filePath = uploadPath.resolve(fileName);
		        String filePath1 = Paths.get(uploadDir, fileName).toString();

		        // 파일 저장
		        Files.copy(file.getInputStream(), Paths.get(filePath1), StandardCopyOption.REPLACE_EXISTING);

		        return fileName;
		    } catch (IOException e) {
		        // 파일 저장 중 오류 발생 시 예외 처리
		        throw new RuntimeException("Failed to save image", e);
		    }
		}

	 
	 	@Transactional
	    public QA createQA(int gid, String comment, String visibility, String loggedInUserId) {
	        // QA 엔터티 생성
		 	Goods goods = goodsRepository.findByGid(gid);
		 	User user = userRepository.findByUserId(loggedInUserId);
	        QA qa = new QA();
	        qa.setGoods(goods);  // Goods 엔터티 생성 후 설정
	        qa.setComment(comment);
	        qa.setUser(user);
	        qa.setPp("public".equals(visibility) ? 1 : 0);  // 공개 여부 설정

	        // QA 엔터티 저장
	        return qaRepository.save(qa);
	    }
	 	
	 	public Review createReview(int gid, int oid, String comment, int star, String loggedInUserId, MultipartFile file) {
	 		String FileName = saveReviewImage(file);
	 		Goods goods = goodsRepository.findByGid(gid);
		 	User user = userRepository.findByUserId(loggedInUserId);
	 		Review review = new Review();
	 		review.setGoods(goods);
	 		review.setUser(user);
	 		review.setStar(star);
	 		review.setComment(comment);
	 		review.setDate(new Date());
	 		review.setMainp(FileName);
	 		Orders orders = ordersRepository.findByOid1(oid);
	 		orders.setRtoken(1);
	 		ordersRepository.save(orders);
	 		return reviewRepository.save(review);
	 	}
}

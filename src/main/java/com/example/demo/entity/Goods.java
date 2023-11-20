package com.example.demo.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Goods {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gid;
	private String gname;
	private int price;
	private String mainp;
	private Date date;
	@OneToOne
	@JoinColumn(name="cid")
	private Category category;
	
	@OneToMany(mappedBy = "goods", cascade = CascadeType.ALL)
	private List<Inventory> inventory;
	
	@OneToMany(mappedBy = "goods", cascade = CascadeType.ALL)
	private List<Images> images;
	
	@OneToOne
	@JoinColumn(name="infoid")
	private GoodsInfo goodsInfo;
	
	@ManyToOne
	@JoinColumn(name="oid")
	private Orders orders;
	
	@ManyToOne
	@JoinColumn(name="bid")
	private Box box;
	
	@OneToMany(mappedBy = "goods", cascade = CascadeType.ALL)
	private List<Review> review;
	
	@OneToMany(mappedBy = "goods", cascade = CascadeType.ALL)
	private List<QA> qa;
	
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getMainp() {
		return mainp;
	}
	public void setMainp(String mainp) {
		this.mainp = mainp;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Inventory> getInventory() {
		return inventory;
	}
	public void setInventory(List<Inventory> inventory) {
		this.inventory = inventory;
	}
	public List<Images> getImages() {
		return images;
	}
	public void setImages(List<Images> images) {
		this.images = images;
	}
	public GoodsInfo getGoodsInfo() {
		return goodsInfo;
	}
	public void setGoodsInfo(GoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}
	public Orders getOrder() {
		return orders;
	}
	public void setOrder(Orders orders) {
		this.orders = orders;
	}
	public List<Review> getReview() {
		return review;
	}
	public void setReview(List<Review> review) {
		this.review = review;
	}
	public List<QA> getQa() {
		return qa;
	}
	public void setQa(List<QA> qa) {
		this.qa = qa;
	}
	public Box getBox() {
		return box;
	}
	public void setBox(Box box) {
		this.box = box;
	}
	
	
}

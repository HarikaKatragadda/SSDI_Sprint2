package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name = "product")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="product_id")
	private int productId;
	@Column(name="productname")
	private String productName;
	@Column(name="seller")
	private String seller;
	@Column(name="code")
	private String code;
	@Column(name="description")
	private String desc;
	@Column(name="image")
	private String image;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Product(){}

	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Column(name="price")
	private float price;
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

	

	
}

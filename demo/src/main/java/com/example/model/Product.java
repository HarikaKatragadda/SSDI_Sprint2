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
	public Product(int productId, String productName, String seller, String code, String desc, String image,
			float price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.seller = seller;
		this.code = code;
		this.desc = desc;
		this.image = image;
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Product(){}

	
	public Product(Product find, int i) {
		// TODO Auto-generated constructor stub
	}
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

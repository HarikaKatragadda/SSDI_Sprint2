package com.example.model;

public class Item {

	private Product product = new Product();
	private int quantity;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Item() {
		super();
	}
	public Item(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}
}

package com.example.test.model;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.model.Product;

public class ProductTest{
	
	private Product test_product;
	@Test
	public void testProductId() {
		
		test_product = new Product();
		test_product.setProductId(5);
	assertEquals(5,test_product.getProductId());
	}
	@Test
	public void testProductName() {
		
		test_product = new Product();
		test_product.setProductName("laptop");
	assertEquals("laptop",test_product.getProductName());
	}
	@Test
	public void testCode() {
		
		test_product = new Product();
		test_product.setCode("CODE123");
	assertEquals("CODE123",test_product.getCode());
	}
	@Test
	public void testSeller() {
		
		test_product = new Product();
		test_product.setSeller("HP");
	assertEquals("HP",test_product.getSeller());
	}
	@Test
	public void testImage() {
		
		test_product = new Product();
		test_product.setImage("https://HP");
	assertEquals("https://HP",test_product.getImage());
	}
}
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
}
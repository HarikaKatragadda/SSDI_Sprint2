package com.example.test.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.BeforeClass;

import com.example.model.Item;
import com.example.model.Product;

public class ItemTest {

	private static Product prod;
	private static Item testItem;
	
	@BeforeClass
	public static void SetUp() {
		prod = new Product(25, "Shoe","sfe","ewf","fewf","feg",34.0f);
		testItem = new Item(prod, 1);
	}
	

	@Test
	public void testSetProduct() {
		Product p1 = new Product(34, "Sock", "abc", "def", "ghi", "jkl", 55.04f);
		testItem.setProduct(p1);
		assertEquals(p1, testItem.getProduct());
	}

	@Test
	public void testSetQuantity() {
		testItem.setQuantity(55);
		assertEquals(55, testItem.getQuantity());
	}

	@Test
	public void testItem() {
		testItem = new Item();
		assertNotNull(testItem);
	}

	@Test
	public void testItemProductInt() {
		testItem = new Item(prod, 1);
		assertNotNull(testItem);
	}

}

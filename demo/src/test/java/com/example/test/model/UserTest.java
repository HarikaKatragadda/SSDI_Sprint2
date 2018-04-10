package com.example.test.model;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.model.Product;
import com.example.model.User;

public class UserTest {
	
	private User test_user;
	
	@Test
	public void testId() {
		
		test_user = new User();
		test_user.setId(5);
	assertEquals(5,test_user.getId());
	}
	@Test
	public void testPassword() {
		
		test_user = new User();
		test_user.setPassword("12345");
	assertEquals("12345",test_user.getPassword());
	}
	@Test
	public void testName() {
		
		test_user = new User();
		test_user.setName("harika");
	assertEquals("harika",test_user.getName());
	}
	@Test
	public void testEmail() {
		
		test_user = new User();
		test_user.setEmail("h@gmail");
	assertEquals("h@gmail",test_user.getEmail());
	}
	@Test
	public void testActive() {
		
		test_user = new User();
		test_user.setActive(1);
	assertEquals(1,test_user.getActive());
	}
/*	@Test
	public void testRoles() {
		
		test_user = new User();
		test_user.setRoles("abc");
	assertEquals(1,test_user.getRoles());
	}*/
}

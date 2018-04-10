package com.example.test.model;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.model.Product;
import com.example.model.Role;

public class RoleTest {

	private Role test_role;
	
	@Test
	public void testId() {
		
		test_role = new Role();
		test_role.setId(5);
		assertEquals(5,test_role.getId());
	}
	@Test
	public void testRole() {
		
		test_role = new Role();
		test_role.setRole("Admin");
		assertEquals("Admin",test_role.getRole());
	}
}

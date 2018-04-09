package com.example.test.service;

import static org.mockito.Mockito.mock;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.model.Product;
import com.example.model.User;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import com.example.service.ProductService;
import com.example.service.UserService;

import mockit.Expectations;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
@SpringBootTest
public class UserServiceImplTest {
	private static UserRepository userRepository;

	private static UserService userService;
	private static User u1;
	
	@BeforeClass
	public static void setUp(){
		u1 = mock(User.class);
		u1=new User();
		u1.setId(9);
		u1.setPassword("12345");
		u1.setName("harika");
		u1.setLastName("ka");
		u1.setEmail("h@gmail.com");
		u1.setActive(1);
		//u1.setRoles("");
		userRepository=mock(UserRepository.class);
		userService=mock(UserService.class);
	}
	@Test
	public void testsaveUser()
	{		
		new Expectations(){{
			userRepository.save(u1);
	
		}};
		
		userService.saveUser(u1);
	}
	@Test
	public void testfindUser()
	{		
		new Expectations(){{
			userRepository.findByEmail("h@gmail");
	
		}};
		
		userService.findUserByEmail("h@gmail");
	}
}

package com.example.test.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import mockit.*;

import com.example.controller.AdminController;
import com.example.controller.LoginController;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.ProductService;
import com.example.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static
org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(JMockit.class)
@SpringBootTest
public class LoginControllerTest {

//	@Tested(availableDuringSetup=true)
	private LoginController login_Controller;
	
	@Injectable
	ProductService productService;
	
	@Injectable
	static
	 UserService userService;
	private static UserRepository userRepository;
	private MockMvc mock_mvc;
	private ObjectMapper obj_mapper;
	
	@Before
	public void init() {
	obj_mapper = new ObjectMapper();
	mock_mvc = MockMvcBuilders.standaloneSetup(login_Controller).build
	();
	}

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
	public void testadminHome() {
		
		
		login_Controller.registration();
		
		new Expectations() {{
			userService.saveUser(u1);
		 }};
		 try {
			  mock_mvc.perform(post("/registration")
			 .contentType(MediaType.APPLICATION_JSON)
			 .content(obj_mapper.writeValueAsString(u1)))
			  .andExpect(status().isOk());
			  } catch (Exception e) {
			  e.printStackTrace();
			  }
	}
}

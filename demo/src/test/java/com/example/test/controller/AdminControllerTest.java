package com.example.test.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
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

import com.example.controller.AdminController;
import com.example.model.User;
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
public class AdminControllerTest {

	@Tested
	private AdminController admin_Controller;
	
	@Injectable
	ProductService productService;
	
	@Injectable
	 UserService userService;
	
	private MockMvc mock_mvc;
	private ObjectMapper obj_mapper;
	
	@Before
	public void init() {
	obj_mapper = new ObjectMapper();
	mock_mvc = MockMvcBuilders.standaloneSetup(admin_Controller).build
	();
	}
	@Test
	public void testadminHome() {
//		admin_Controller.registration();
		
		new Expectations() {{
			//		userService.saveUser(test_building);
		 }};
		 try {
			  mock_mvc.perform(get("/registration")
			 .contentType(MediaType.APPLICATION_JSON)
			 .content(obj_mapper.writeValueAsString(userService)))
			  .andExpect(status().isOk());
			  } catch (Exception e) {
			  e.printStackTrace();
			  }
	}
}

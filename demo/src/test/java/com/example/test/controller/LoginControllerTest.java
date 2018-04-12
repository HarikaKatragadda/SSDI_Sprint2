package com.example.test.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.*;

import com.example.configuration.SecurityConfiguration;
import com.example.configuration.WebMvcConfig;
import com.example.controller.LoginController;
import com.example.service.ProductService;
import com.example.model.Product;
import com.example.service.UserService;
import com.example.model.User;
import com.example.model.Role;

import mockit.MockUp;
import mockit.integration.junit4.JMockit;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.LinkedList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;


@RunWith(JMockit.class)
@SpringBootTest
public class LoginControllerTest {
private MockMvc mockMvc;
	
	@Mock
    private ProductService productServiceMock;
	
	@Mock
	private UserService userServiceMock;
	
	@Mock
	private User user;
	
	@Mock
	private SecurityContext securityContextMock;
	
	@Mock
	private Authentication auth;
	
    @InjectMocks
    private LoginController loginController;
	
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("email@login.com", "Password"));
        mockMvc = MockMvcBuilders
                .standaloneSetup(loginController)
                .build();
    }
    @Test
	public void registrationTest() throws Exception{
		ModelAndView mv = loginController.registration();
		assertEquals("registration", mv.getViewName());
	}
	
	@Test 
	public void loginTest() throws Exception{
		ModelAndView mv = loginController.login();
		assertEquals("login", mv.getViewName());
	}
    @Test
    public void loginPage() throws Exception {
    	mockMvc.perform(get("/"))
    	.andExpect(status().isOk())
    	.andExpect(view().name("login"));
    }
    
    @Test
    public void adminHomePage() throws Exception {
    	User user = new User();
    	user.setId(18);
    	user.setEmail("email@domain.com");
    	user.setName("First");
    	user.setLastName("Last");
    	user.setPassword("Password");
    	user.setActive(1);
    	user.setRoles(new HashSet<Role>() );
    	Product prod = new Product(25, "Shoe","sfe","ewf","fewf","feg",34);
    	LinkedList<Product> prodList = new LinkedList<Product>();
    	prodList.add(prod);
    	when(auth.getName()).thenReturn("email@domain.com");
    	when(securityContextMock.getAuthentication()).thenReturn(auth);
    	SecurityContextHolder.setContext(securityContextMock);
    	when(userServiceMock.findUserByEmail("email@domain.com")).thenReturn(user);
    	when(productServiceMock.listProducts()).thenReturn(prodList);
    	mockMvc.perform(get("/admin/home"))
    	.andExpect(status().isOk())
    	.andExpect(view().name("admin/home"))
    	.andExpect(model().attributeExists("listProducts"))
    	.andReturn();
    }
}

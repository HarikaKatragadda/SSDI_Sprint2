package com.example.test.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;


import com.example.controller.AdminController;
import com.example.controller.UserController;
import com.example.service.ProductService;
import com.example.model.Product;

import mockit.integration.junit4.JMockit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;



@RunWith(JMockit.class)
@SpringBootTest
public class UserControllerTest {

	private MockMvc mockMvc;
	
	@Mock
    private ProductService productServiceMock;

    @InjectMocks
    private UserController userController;
	
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
    }
    @Test
	public void testuserproducts(){
		ModelAndView mv = userController.userproducts();
		
		assertEquals("/userpages/userview", mv.getViewName());
    }
   @Test
   	public void testabout(){
   		ModelAndView mv = userController.about();
   		assertEquals("/userpages/about", mv.getViewName());
       }
    @Test
   	public void testcontact(){
   		ModelAndView mv = userController.contact();
   		assertEquals("/userpages/contactus", mv.getViewName());
       }
}

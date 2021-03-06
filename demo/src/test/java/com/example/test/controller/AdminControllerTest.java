package com.example.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.example.controller.AdminController;
import com.example.service.ProductService;

import junit.framework.Assert;

import com.example.model.Product;

import mockit.integration.junit4.JMockit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;



@RunWith(JMockit.class)
@SpringBootTest
public class AdminControllerTest {

	private MockMvc mockMvc;
	
	@Mock
    private ProductService productServiceMock;

    @InjectMocks
    private AdminController adminController;
	
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/resources/templates/");
        viewResolver.setSuffix(".html");
        mockMvc = MockMvcBuilders
                .standaloneSetup(adminController)
                .setViewResolvers(viewResolver)
                .build();
    }
    
	@Test
    public void addProductPage() throws Exception{
		ModelAndView mv = adminController.addProduct();
		assertEquals("/admin/addproduct", mv.getViewName());
    }

	@Test
	public void editProductPage() throws Exception{
		when(productServiceMock.getProduct(25)).thenReturn(new Product(25, "Shoe","sfe","ewf","fewf","feg",34.0f));
        mockMvc.perform(get("/admin/editproduct").param("productId", "25"))
	        .andExpect(status().isOk())
	        .andExpect(view().name("/admin/addproduct"))
	        .andExpect(model().attributeExists("product"))
	        .andReturn();
	}
	@Test
	public void deleteProductPage() throws Exception{
		when(productServiceMock.getProduct(25)).thenReturn(new Product(25, "Shoe","sfe","ewf","fewf","feg",34));
		productServiceMock.delete(25);
        mockMvc.perform(get("/admin/deleteproduct").param("productId", "25"))
	        .andExpect(status().is3xxRedirection())
	        .andExpect(view().name("redirect:/admin/home"))
	        .andExpect(model().attributeDoesNotExist("product"))
	        .andReturn();
		/*MockHttpServletRequest request = new MockHttpServletRequest();
		   request.addParameter("productId", "25");
		ModelAndView modelAndView 
		= adminController.deleteProduct(request);
		verify(productServiceMock,times(1)).delete(25);*/

	}
	@Test
	public void saveProductPage() throws Exception{
		Product p1 = new Product(25, "Shoe","sfe","ewf","fewf","feg",34.0f);
		ModelAndView mv = adminController.saveProduct(p1);
		assertEquals("redirect:/", mv.getViewName());
	}
	@Test
	public void testcreateNewProduct() throws Exception{
		when(productServiceMock.getProduct(25)).thenReturn(new Product(25, "Shoe","sfe","ewf","fewf","feg",34.0f));
        mockMvc.perform(get("/admin/addproduct").param("image", "feg"))
	        .andExpect(status().isOk())
	        .andExpect(view().name("/admin/addproduct"))
	        .andExpect(model().attributeExists("product"))
	        .andReturn();
	}
	
}
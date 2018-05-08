package com.example.test.controller;

import com.example.model.Item;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;


import com.example.controller.UserController;
import com.example.service.ProductService;

import java.util.List;
import java.util.ArrayList;

import com.example.model.Product;

import mockit.integration.junit4.JMockit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.mock;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



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
    @SuppressWarnings("unchecked")
	@Test
    public void testcart() {
    	HttpServletRequest request = mock(HttpServletRequest.class);
    	when(request.getParameter("productId")).thenReturn("1");
    	HttpSession session = mock(HttpSession.class);
    	List<Item> cart = new ArrayList<Item>();
    	Product prod = new Product(1, "name", "seller", "code", "desc", "image", 10.0f);
    	when(productServiceMock.getProduct(1)).thenReturn(prod);
    	cart.add(new Item(prod, 1));
    	when(session.getAttribute("cart")).thenReturn(cart);
    	ModelAndView mv = userController.myCart(request, session);
    	assertEquals(prod, ((List<Item>) mv.getModel().get("cart")).get(0).getProduct());
    	assertEquals(2, ((List<Item>) mv.getModel().get("cart")).get(0).getQuantity());

    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testCartNull() {
    	HttpServletRequest request = mock(HttpServletRequest.class);
    	when(request.getParameter("productId")).thenReturn("1");
    	HttpSession session = mock(HttpSession.class);
    	when(session.getAttribute("cart")).thenReturn(null);
    	Product prod = new Product(1, "name", "seller", "code", "desc", "image", 10.0f);
    	when(productServiceMock.find(1)).thenReturn(prod);
    	ModelAndView mv = userController.myCart(request, session);
    	assertEquals(prod, ((List<Item>) mv.getModel().get("cart")).get(0).getProduct());
    	assertEquals(1, ((List<Item>) mv.getModel().get("cart")).get(0).getQuantity());

    }
    
    @Test
    public void testDeleteCart() {
    	HttpServletRequest request = mock(HttpServletRequest.class);
    	when(request.getParameter("productId")).thenReturn("1");
    	HttpSession session = mock(HttpSession.class);
    	List<Item> cart = new ArrayList<Item>();
    	Product prod = new Product(1, "name", "seller", "code", "desc", "image", 10.0f);
    	cart.add(new Item(prod, 1));
    	when(session.getAttribute("cart")).thenReturn(cart);
    	ModelAndView mv = userController.deleteCart(request, session);
    	assertEquals(new ArrayList<Item>(), mv.getModel().get("cart"));
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testCartUpdate() {
    	HttpServletRequest request = mock(HttpServletRequest.class);
    	when(request.getParameter("productId")).thenReturn("1");
    	String[] quantity = {"2"};
    	when(request.getParameterValues("quantity")).thenReturn(quantity);
    	HttpSession session = mock(HttpSession.class);
    	List<Item> cart = new ArrayList<Item>();
    	Product prod = new Product(1, "name", "seller", "code", "desc", "image", 10.0f);
    	when(productServiceMock.getProduct(1)).thenReturn(prod);
    	cart.add(new Item(prod, 1));
    	when(session.getAttribute("cart")).thenReturn(cart);
    	ModelAndView mv = userController.cartUpdate(request, session);
    	assertEquals(prod, ((List<Item>) mv.getModel().get("cart")).get(0).getProduct());
    	assertEquals(2, ((List<Item>) mv.getModel().get("cart")).get(0).getQuantity());

    }
    
    @Test
    public void testShoppingCartCustomer() {
    	HttpServletRequest request = mock(HttpServletRequest.class);
    	HttpSession session = mock(HttpSession.class);
    	ModelAndView mv = userController.checkout(request, session);
    	assertEquals("/userpages/shoppingCartCustomer", mv.getViewName());
    }
}

package com.example.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Item;
import com.example.model.Product;
import com.example.model.User;
import com.example.service.ProductService;
@Controller
public class UserController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/userpages/userview", method = RequestMethod.GET)
	public ModelAndView userproducts(){
		ModelAndView modelAndView=null;
		 modelAndView = new ModelAndView("/userpages/userview");
		List<Product> listProducts = productService.listProducts();
		modelAndView.addObject("listProducts" , listProducts);
		modelAndView.setViewName("/userpages/userview");
		return modelAndView;
	}
	@RequestMapping(value="/userpages/about", method = RequestMethod.GET)
	public ModelAndView about(){
		ModelAndView modelAndView=null;
		 modelAndView = new ModelAndView("/userpages/about");
		modelAndView.setViewName("/userpages/about");
		return modelAndView;
		//return new ModelAndView();
	}
	@RequestMapping(value="/userpages/contactus", method = RequestMethod.GET)
	public ModelAndView contact(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/userpages/contactus");
		return modelAndView;
		//return new ModelAndView();
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/userpages/shoppingCart", method = RequestMethod.GET)
	public ModelAndView myCart(HttpServletRequest request,HttpSession session){
		ModelAndView modelAndView = new ModelAndView();
		if (request.getParameter("productId") == null) {
			if (session.getAttribute("cart") == null) {
				List<Item> cart = new ArrayList<Item>();
				session.setAttribute("cart", cart);
				modelAndView.addObject("cart", cart);
			}
			return modelAndView;
		}
		
		int productId = Integer.parseInt(request.getParameter("productId"));
		 
		if (session.getAttribute("cart") == null) {
			   List<Item> cart = new ArrayList<Item>();
			   cart.add(new Item(this.productService.find(productId), 1));
			   session.setAttribute("cart", cart);
				 modelAndView.addObject("cart",cart);
			  }
		 else
		 {
			 List<Item> cart = (List<Item>)session.getAttribute("cart");
			 int index = isExisting(productId,session);
			 if(index==-1)
			 cart.add(new Item(this.productService.find(productId), 1));
			 else{
				 int quantity = cart.get(index).getQuantity()+1;
				 cart.get(index).setQuantity(quantity);
			 }
			   session.setAttribute("cart", cart);
			   modelAndView.addObject("cart",cart);
		 }
		// modelAndView.setViewName("/userpages/shoppingCart");
		return modelAndView; 
	}
	
	@RequestMapping(value="/userpages/deleteCart", method = RequestMethod.GET)
	public ModelAndView deleteCart(HttpServletRequest request,HttpSession session){
		ModelAndView modelAndView = new ModelAndView();
		int productId = Integer.parseInt(request.getParameter("productId"));
		 List<Item> cart = (List<Item>)session.getAttribute("cart");
		 int index = isExisting(productId,session);
		 cart.remove(index);
		 session.setAttribute("cart", cart);
		 modelAndView.addObject("cart",cart);
		 modelAndView.setViewName("/userpages/shoppingCart");
		 return modelAndView;
	}
	@RequestMapping(value="/userpages/cartUpdate", method = RequestMethod.POST)
	public ModelAndView cartUpdate(HttpServletRequest request,HttpSession session){
		ModelAndView modelAndView = new ModelAndView();
		 List<Item> cart = (List<Item>)session.getAttribute("cart");
		 String [] quantity = request.getParameterValues("quantity");
		 for(int i=0;i < cart.size();i++){
			 cart.get(i).setQuantity(Integer.parseInt(quantity[i]));
		 }
		 
		 session.setAttribute("cart", cart);
		 modelAndView.addObject("cart",cart);
		 modelAndView.setViewName("/userpages/shoppingCart");
		 return modelAndView;
	}
	
	@RequestMapping(value="/userpages/shoppingCartCustomer", method = RequestMethod.GET)
	public ModelAndView checkout(HttpServletRequest request,HttpSession session){
		ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("/userpages/shoppingCartCustomer");
		 return modelAndView;
	}
	
	@RequestMapping(value="/userpages/paymentform", method = RequestMethod.GET)
	public ModelAndView payment(HttpServletRequest request,HttpSession session){
		ModelAndView modelAndView = new ModelAndView();
		 List<Item> cart = (List<Item>)session.getAttribute("cart");
		 modelAndView.setViewName("/userpages/paymentform");
		 return modelAndView;
	}
	
	@RequestMapping(value="/userpages/shoppingCartCustomer", method = RequestMethod.POST)
	public ModelAndView saveOrder(HttpServletRequest request,HttpSession session){
		ModelAndView modelAndView = new ModelAndView();
		 List<Item> cart = (List<Item>)session.getAttribute("cart");
		 String firstName = request.getParameter("firstName");
		 String lastName = request.getParameter("lastName");
		 String address = request.getParameter("address");
		 String email = request.getParameter("email");
		 String phone = request.getParameter("phone");
		 modelAndView.addObject("cart",cart);
		 modelAndView.setViewName("/userpages/paymentform");
		 return modelAndView;
	}
	
	private int isExisting(int productId,HttpSession session ){
		List<Item> cart = (List<Item>)session.getAttribute("cart");
		for(int i=0;i < cart.size();i++)
			if(cart.get(i).getProduct().getProductId()== productId)
			return i;
		return -1;	
		
	}
}

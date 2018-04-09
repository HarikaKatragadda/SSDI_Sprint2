package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Product;
import com.example.model.User;
import com.example.service.ProductService;
@Controller
public class UserController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/userpages/userview", method = RequestMethod.GET)
	public ModelAndView userproducts(){
		ModelAndView modelAndView = new ModelAndView();
		List<Product> listProducts = productService.listProducts();
		modelAndView.addObject("listProducts" , listProducts);
		modelAndView.setViewName("/userpages/userview");
		return modelAndView;
	}
	@RequestMapping(value="/userpages/about", method = RequestMethod.GET)
	public ModelAndView about(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/userpages/about");
		//return modelAndView;
		return new ModelAndView();
	}
	@RequestMapping(value="/userpages/contactus", method = RequestMethod.GET)
	public ModelAndView contact(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/userpages/contactus");
		//return modelAndView;
		return new ModelAndView();
	}
}

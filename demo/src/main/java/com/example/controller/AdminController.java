package com.example.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Product;
import com.example.service.ProductService;


@Controller
public class AdminController {
	
	@Autowired
	private ProductService productService;
	@RequestMapping(value="/admin/addproduct", method = RequestMethod.GET)
	public ModelAndView addProduct() {
		ModelAndView modelAndView = new ModelAndView();
		Product product = new Product();
		modelAndView.addObject("product", product);
		modelAndView.setViewName("/admin/addproduct");
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/addproduct", method = RequestMethod.POST)
	public ModelAndView createNewProduct(@Valid Product product, BindingResult bindingResult, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("/admin/addproduct");
		} else {
//			product.setImage(request.getParameter("imgURL"));
			product.setImage(request.getParameter("imgURL"));
			System.out.println("Image is "+product.getImage());
			productService.addProduct(product);
			modelAndView.addObject("successMessage", "Product has been added successfully");
			modelAndView.addObject("product", new Product());
			modelAndView.setViewName("/admin/addproduct");
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/editproduct", method = RequestMethod.GET)
	public ModelAndView editProduct(HttpServletRequest request) {
	    int productId = Integer.parseInt(request.getParameter("productId"));
	     Product product= productService.getProduct(productId);
	     ModelAndView model = new ModelAndView("/admin/addproduct");
	     model.addObject("product", product);
	   //  model.addObject("product", "Welcome " + product.getSeller()); 
	     return model;
	    
	}
	@RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
	public ModelAndView saveProduct(@ModelAttribute Product product) {
		productService.saveOrUpdate(product);
	    return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value="/admin/deleteproduct", method = RequestMethod.GET)
	public ModelAndView deleteProduct(HttpServletRequest request) {
	    int productId = Integer.parseInt(request.getParameter("productId"));
	    productService.delete(productId);
	    return new ModelAndView("redirect:/admin/home");
	    }
}

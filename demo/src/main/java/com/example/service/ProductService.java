package com.example.service;

import com.example.model.Product;


import java.util.List;

public interface ProductService {
	public void addProduct(Product product);
	
    public void saveOrUpdate(Product product);
    
    public void delete(int productID);
    
    public Product getProduct(int productID);
    
    public List<Product> listProducts();
}

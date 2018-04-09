package com.example.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
@SpringBootTest
public class ProductServiceImplTest {
	/*@Injectable
	ProductRepository productRepository;*/
	private static ProductRepository productRepository;

	private static ProductService productService;
	private static Product p1;

	
	@BeforeClass
	public static void setUp(){
	p1 = mock(Product.class);
	p1=new Product();
	p1.setProductId(9);
	p1.setSeller("seller1");
	p1.setDesc("desc1");
	p1.setCode("code1");
	p1.setPrice(0);
	p1.setProductName("name1");
	productRepository=mock(ProductRepository.class);
	productService=mock(ProductService.class);
	}
	
	@Test
	public void testaddproduct()
	{		
		new Expectations(){{
			productRepository.save(p1);
	
		}};
		
		productService.addProduct(p1);
	}
	@Test
	public void testsaveOrUpdate()
	{		
		new Expectations(){{
			productService.saveOrUpdate(p1);
	
		}};
		ProductService product = null;
		product.saveOrUpdate(p1);
	}
}

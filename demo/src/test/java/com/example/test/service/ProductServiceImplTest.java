package com.example.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.BeanConfig;
import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;

import junit.framework.Assert;
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
	
	private static EntityManager em;
	@Mock
	private static EntityManager entityManager;
	@Mock
	private static Query query;
	
	@BeforeClass
	public static void setUp(){
	
	productRepository=mock(ProductRepository.class);
	productService=mock(ProductService.class);
	 em = mock(EntityManager.class);
	 
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
	public void testlistProducts()
	{		
		p1 = mock(Product.class);
		p1=new Product();
		p1.setProductId(9);
		p1.setSeller("seller1");
		p1.setDesc("desc1");
		p1.setCode("code1");
		p1.setPrice(0);
		p1.setProductName("name1");
		List<Product> ul=new ArrayList<Product>();
		ul.add(p1);
		when(productService.listProducts()).thenReturn(ul);
		productService.listProducts();
		assertNotNull(productService.listProducts());
		assertEquals("name1",p1.getProductName());
	}
	
	
	@Test
	public void testgetProduct()
	{		
		p1 = mock(Product.class);
		p1=new Product();
		p1.setProductId(9);
		p1.setSeller("seller1");
		p1.setDesc("desc1");
		p1.setCode("code1");
		p1.setPrice(0);
		p1.setProductName("name1");
		//ul.add(p1);
		//when(productService.listProducts()).thenReturn(ul);
		productService.addProduct(p1);
		
		assertNotNull(productService.getProduct(1));
//		assertEquals("name1",p1.getProductName());
	}
	@Test
	public void testsaveorUpdateProduct()
	{		
//		p1 = mock(Product.class);
		p1=new Product();
//		p1.setProductId(9);
		p1.setSeller("seller1");
		p1.setDesc("desc1");
		p1.setCode("code1");
		p1.setPrice(56);
		p1.setProductName("name1");
		p1.setImage("/hhtps");
		int i = productService.saveOrUpdate(p1);		
		assertTrue(i>=0);
	}


}

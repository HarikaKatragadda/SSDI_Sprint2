package com.example.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Product;
import com.example.repository.ProductRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import javax.sql.DataSource;

@Service("productService")
public class ProductServiceImpl implements ProductService{
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private SessionFactory sessionFactory;
	

    private JdbcTemplate jdbcTemplate;
 
    public ProductServiceImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	  @Autowired
	  private EntityManagerFactory entityManagerFactory;
	    //private EntityManager entityManager;

	public void addProduct(Product product){
		System.out.println("in servc");
		productRepository.save(product);
		
	}

	@Override
	@Transactional
	public void saveOrUpdate(Product product){	
		if (product.getProductId() > 0) {
	        // update
	        String sql = "UPDATE product SET productname=?, code=?, price=?, "
	                    + "seller=?,description=?, image=? WHERE product_id=?";
	        jdbcTemplate.update(sql, product.getProductName(), product.getCode(),
	        		product.getPrice(), product.getSeller(),product.getDesc(), product.getProductId(), product.getImage());
	    } else {
	        // insert
	        String sql = "INSERT INTO product (productname, code, price, seller,description, image)"
	                    + " VALUES (?, ?, ?, ?,?,?,?)";
	        jdbcTemplate.update(sql,product.getProductName(), product.getCode(),
	        		product.getPrice(), product.getSeller(),product.getDesc(), product.getProductId(), product.getImage());
	    }
	 	
	}
	@Override
	@Transactional
    public void delete(int productId) {
    	Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
    	session.createQuery("DELETE FROM Product p WHERE product_id=" + productId).executeUpdate();
        
    }
	@Override
	@Transactional
    public List<Product> listProducts() {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
    	List<Product> productList = session.createQuery("select p from Product p").list();
		return productList;
    }
	@Override
	@Transactional
    public Product getProduct(int productId) {
		String sql = "SELECT * FROM Product WHERE product_id=" + productId;
		System.out.println("sql query is"+sql);
	    return jdbcTemplate.query(sql, new ResultSetExtractor<Product>() {
	 
	        @Override
	        public Product extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Product product = new Product();
	                product.setProductId(rs.getInt("product_id"));
	                product.setProductName(rs.getString("productname"));
	                product.setCode(rs.getString("code"));
	                product.setPrice(rs.getInt("price"));
	                product.setSeller(rs.getString("seller"));
	                product.setImage(rs.getString("image"));
	                return product;
	            }
	 
	            return null;
	        }
	 
	    });
    }
	
}

package com.jsp.shopping_cart.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.shopping_cart.dto.Product;

@Repository
public class ProductDao {
	@Autowired
	EntityManagerFactory emf;
	
	public void saveProduct(Product product ) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.persist(product);
		et.commit();
		
	}
	
	public Product login(String email, String password) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select p  from Product p where p.email=?1 and p.password=?2");
		query.setParameter(1, email);
		query.setParameter(2, password);
		
		
		Product product = (Product) query.getSingleResult();
		
		if(product != null) {
			return product;
		}else {
			return null;
		}
	}
	
	public void updateProduct(Product p) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.merge(p);
		et.commit();
		
	}
	
	public void deleteProductById(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		Product p = em.find(Product.class,id);
		et.begin();
		em.remove(p);
		et.commit();
		
		
	}
	
	
	public Product findProductById(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		Product p = em.find(Product.class,id);
		
		if(p !=null) 
			return p;
		else 
			return null;
	
	}
	
	public List<Product>fetchAllProducts(){
		EntityManager em =emf.createEntityManager();
		Query query=em.createQuery("select p from Product p");
		return query.getResultList();
	
	}
	
	public List<Product> findProductByBrand(String brand) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select p from Product p where p.brand=?1");
		query.setParameter(1, brand); 
		
		return query.getResultList();
	
	}
}

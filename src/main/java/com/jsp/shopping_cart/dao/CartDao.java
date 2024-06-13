package com.jsp.shopping_cart.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.shopping_cart.dto.Cart;


@Repository
public class CartDao {
	@Autowired
	EntityManagerFactory emf;
	
	public void saveCart(Cart cart ) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.persist(cart);
		et.commit();
		
	}
	
	public Cart login(String email, String password) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select c  from Cart c where c.email=? and c.password=?2");
		query.setParameter(1, email);
		query.setParameter(2, password);
		
		
		Cart cart = (Cart) query.getSingleResult();
		
		if(cart != null) {
			return cart;
		}else {
			return null;
		}
	}
	
	public void updateCart(Cart c) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.merge(c);
		et.commit();
		
	}
	
	public void deleteCartById(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		Cart c = em.find(Cart.class,id);
		et.begin();
		em.remove(c);
		et.commit();
		
		
	}
	
	
	public Cart findCartById(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		Cart c = em.find(Cart.class,id);
		
		if(c !=null) 
			return c;
		else 
			return null;
	
	}
	
	public Cart removeAllItemsFromCart(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		Cart c = em.find(Cart.class, id);
		c.setItems(null);
		c.setTotalprice(0);
		
		et.begin();
		em.merge(c);
		et.commit();
		
		return c;
	}
	
	public void removeItemFromCartById(int cart_id, int item_id) {
		
	}
}



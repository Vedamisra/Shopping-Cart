package com.jsp.shopping_cart.dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.shopping_cart.dto.Orders;



@Repository
public class OrdersDao {
	@Autowired
	EntityManagerFactory emf;
	
	public void saveOrder(Orders order ) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.persist(order);
		et.commit();
		
	}
	
	public Orders login(String email, String password) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select o  from Order o where o.email=? and o.password=?2");
		query.setParameter(1, email);
		query.setParameter(2, password);
		
		
		Orders order = (Orders) query.getSingleResult();
		
		if(order != null) {
			return order;
		}else {
			return null;
		}
	}
	
	public void updateOrder(Orders o) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.merge(o);
		et.commit();
		
	}
	
	public void deleteOrderById(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		Orders o = em.find(Orders.class,id);
		et.begin();
		em.remove(o);
		et.commit();
		
		
	}
	
	
	public Orders findOrderById(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		Orders o = em.find(Orders.class,id);
		
		if(o !=null) 
			return o;
		else 
			return null;
	
	}
}


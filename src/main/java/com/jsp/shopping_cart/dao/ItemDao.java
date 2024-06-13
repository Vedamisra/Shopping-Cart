package com.jsp.shopping_cart.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.shopping_cart.dto.Item;



@Repository
public class ItemDao {
	@Autowired
	EntityManagerFactory emf;
	
	public void saveItem(Item item) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.persist(item);
		et.commit();
		
	}
	
	public Item login(String email, String password) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select i  from Item i where i.email=?1 and i.password=?2");
		query.setParameter(1, email);
		query.setParameter(2, password);
		
		
		Item item = (Item) query.getSingleResult();
		
		if(item != null) {
			return item;
		}else {
			return null;
		}
	}
	
	public void updateItem(Item i) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.merge(i);
		et.commit();
		
	}
	
	public void deleteItemById(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		Item i = em.find(Item.class,id);
		et.begin();
		em.remove(i);
		et.commit();
		
		
	}
	
	
	public Item findItemById(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		Item i = em.find(Item.class,id);
		
		if(i !=null) 
			return i;
		else 
			return null;
	
	}
}


package com.grocery.booking.SpringBootGroceryApplication.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.grocery.booking.SpringBootGroceryApplication.model.Grocery;

@Repository
public class GroceryDao {

	
	@Autowired
    private SessionFactory sf;
	
	private Session s=null;
	
	
	 @SuppressWarnings({ "deprecation", "unchecked" })
	public List<Grocery> findAll() {
	        s = sf.getCurrentSession();
	        List<Grocery> list = s.createCriteria(Grocery.class).list();
	        return list;
	    }
	 
	 public Grocery findById(int groceryId) {
	        s = sf.getCurrentSession();
	        Grocery grocery = s.find(Grocery.class, groceryId);
	        return grocery;
	    }
	 
	 public void removeById(int groceryId) {
	        s = sf.getCurrentSession();
	        Grocery grocery = findById(groceryId);
	        s.beginTransaction();
	       s.remove(grocery);
	       s.getTransaction().commit();
	       s.getTransaction().rollback();
	    }
	 
	 public Integer addGroceryItem(Grocery grocery) {
	        s = sf.getCurrentSession();
	        s.beginTransaction();
	        Integer id = (Integer) s.save(grocery);
	        s.getTransaction().commit();
	        s.getTransaction().rollback();
	        return id;
	    }
	 
	 public void updateGroceryItem(Grocery grocery) {
	        s = sf.getCurrentSession();
	        s.beginTransaction();
	        s.update(grocery);
	        
	        s.getTransaction().commit();
	        s.getTransaction().rollback();
	    }
}

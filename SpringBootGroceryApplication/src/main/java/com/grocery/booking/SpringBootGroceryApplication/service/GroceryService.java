package com.grocery.booking.SpringBootGroceryApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.booking.SpringBootGroceryApplication.dao.GroceryDao;
import com.grocery.booking.SpringBootGroceryApplication.model.Grocery;

@Service
public class GroceryService {
	
	@Autowired
    private GroceryDao groceryDao;
	
	
	public int orderGrocery(List<Grocery> listGrocery) {
		int totalPrice = 0;
		try {
		List<Grocery> existingGroceries = groceryDao.findAll();
		for(int i =0; i<listGrocery.size(); i++) {
			totalPrice= totalPrice + (listGrocery.get(i).getProductPrice()*listGrocery.get(i).getProductQuantity());
		}
		listGrocery.forEach(orderedGrocery -> {
			existingGroceries.forEach(existingGrocery->{
				if(orderedGrocery.getId() == existingGrocery.getId()) {
					int oldQuantity = existingGrocery.getProductQuantity();
					int newQuantity = orderedGrocery.getProductQuantity();
					existingGrocery.setProductQuantity(oldQuantity-newQuantity);
				}
			});
		});
		existingGroceries.forEach(g ->{
			groceryDao.updateGroceryItem(g);
		});
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
		return totalPrice;
		
	}
	

}

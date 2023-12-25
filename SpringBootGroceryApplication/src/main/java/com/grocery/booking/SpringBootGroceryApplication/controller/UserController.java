package com.grocery.booking.SpringBootGroceryApplication.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.booking.SpringBootGroceryApplication.dao.GroceryDao;
import com.grocery.booking.SpringBootGroceryApplication.model.Grocery;
import com.grocery.booking.SpringBootGroceryApplication.service.GroceryService;


@RestController
public class UserController {
	
	@Autowired
    private GroceryDao groceryDao;
	
	@Autowired
    private GroceryService groceryService;
	
	
	@GetMapping(value= "user/get-all-grocery")
    public List<Grocery> getAllGrocery() {
        return groceryDao.findAll().stream().filter(g -> g.getProductQuantity()>0).collect(Collectors.toList());
    }
	
	
	@PostMapping(value= "user/order-grocery")
    public int orderGrocery(@RequestBody List<Grocery> listGrocery) {
		if(!CollectionUtils.isEmpty(listGrocery)) {
			return groceryService.orderGrocery(listGrocery);
		}
		return 0;
    }

}

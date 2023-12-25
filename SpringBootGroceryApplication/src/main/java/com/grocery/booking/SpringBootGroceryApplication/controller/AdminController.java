package com.grocery.booking.SpringBootGroceryApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.booking.SpringBootGroceryApplication.dao.GroceryDao;
import com.grocery.booking.SpringBootGroceryApplication.model.Grocery;

@RestController
public class AdminController {

	@Autowired
    private GroceryDao groceryDao;
	
	@GetMapping(value= "/admin/get-all-groceries")
    public List<Grocery> findAll() {
        return groceryDao.findAll();
    }
	
	@GetMapping(value="/admin/remove-grocery/{groceryId}")
	public String removeGroveryItem(@PathVariable("groceryId") String groceryId) {
		try {
         groceryDao.removeById(Integer.parseInt(groceryId));
		}catch(Exception e) {
			e.printStackTrace();
			return "Error occured while removing grocery";
		}
		return "Removed Successfully";
    }
	
	@PostMapping(value="/admin/add-grocery")
	public String addGroveryItem(@RequestBody Grocery grocery) {
		try {
         groceryDao.addGroceryItem(grocery);
		}catch(Exception e) {
			e.printStackTrace();
			return "Error occured while Adding grocery";
		}
		return "Added Successfully";
    }
	
	@PostMapping(value="/admin/update-grocery")
	public String updateGroceryItems(@RequestBody List<Grocery> listGrocery) {
		try {
			listGrocery.forEach(g ->{
				groceryDao.updateGroceryItem(g);
			});
        }catch(Exception e) {
			e.printStackTrace();
			return "Error occured while Updating grocery";
		}
		return "Updated Successfully";
    }
	
}

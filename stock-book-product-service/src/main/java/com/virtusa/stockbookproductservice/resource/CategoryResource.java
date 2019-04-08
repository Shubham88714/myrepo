package com.virtusa.stockbookproductservice.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.stockbookproductservice.domain.Category;
import com.virtusa.stockbookproductservice.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryResource {

	@Autowired
	CategoryService categoryService;
	
	//save 
	@PostMapping("/category")
	public ResponseEntity<Category> saveCategory(@RequestBody  Category category) throws URISyntaxException
	{
		Category theCategory = categoryService.saveCategory(category);
		if(theCategory!=null)
			return ResponseEntity.created(new URI("/api/category/"+theCategory.getId())).build();
		
		else
			return ResponseEntity.badRequest().build();
	}
	
	//delete
	@DeleteMapping("/category/{id}") 
	public ResponseEntity<Category> deleteCategory(@PathVariable("id") Long id)
	{
		Category theCategory = categoryService.deleteCategory(id);
		if(theCategory!=null)
			return new ResponseEntity<Category>(theCategory,HttpStatus.OK);
		else
			return new ResponseEntity<Category>(theCategory,HttpStatus.NOT_FOUND);
	}
	
	//get list of category
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategories()
	{
		List<Category> categories = categoryService.getAllCategories();
		return new ResponseEntity<List<Category>>(categories,HttpStatus.OK);
	}
	
}

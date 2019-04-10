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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public ResponseEntity<String> saveCategory(@RequestBody  Category category) throws URISyntaxException
	{
		Category theCategory = categoryService.saveCategory(category);
		
			return ResponseEntity.created(new URI("/api/category/"+theCategory.getId())).build();
	}
	
	//get by id
	@GetMapping("/category/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id)
	{
		Category theCategory = categoryService.getCategoryById(id);
		if(theCategory!=null)
			return new ResponseEntity<Category>(theCategory,HttpStatus.OK);
		else
			return new ResponseEntity<Category>(theCategory,HttpStatus.BAD_REQUEST);
	}
	
	
	//delete
	@DeleteMapping("/category/{id}") 
	@ResponseBody
	public ResponseEntity<Category> deleteCategory(@PathVariable("id") Long id)
	{
		Category theCategory = categoryService.deleteCategory(id);
		if(theCategory!=null)
			return new ResponseEntity<Category>(theCategory,HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<Category>(theCategory,HttpStatus.BAD_REQUEST);
	}
	
	//get list of category
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategories()
	{
		List<Category> categories = categoryService.getAllCategories();
		return new ResponseEntity<List<Category>>(categories,HttpStatus.OK);
	}
	
	
	//update category
	@PutMapping("/category/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id,@RequestBody Category category)
	{
		Category theCategory = categoryService.updateCategoryById(id,category);
		if(theCategory!=null)
			return new ResponseEntity<Category>(theCategory,HttpStatus.OK);
		else
			return new ResponseEntity<Category>(theCategory,HttpStatus.BAD_REQUEST);
	}
	
	
	
}

package com.virtusa.stockbookproductservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.stockbookproductservice.domain.Category;
import com.virtusa.stockbookproductservice.repository.ICategoryRepository;

@Service
public class CategoryService {

	@Autowired
	ICategoryRepository categoryRepository;

	//save
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	//delete by id
	public Category deleteCategory(Long id) {
		Category theCategory = null;
		Optional<Category> optCategory = categoryRepository.findById(id);

		if (optCategory != null) {
			theCategory = optCategory.get();
			categoryRepository.delete(theCategory);
		}
		return theCategory;
	}

	//update by id
	public Category updateCategory(Category category)
	{
		Category theCategory = null;
		Optional<Category> optCategory = categoryRepository.findById(category.getId());

		if (optCategory != null) {
			theCategory = optCategory.get();
			theCategory.setName(category.getName());
			
			return categoryRepository.save(theCategory);
		}
		return theCategory;
	}
	
	//get list of saved category
	public List<Category> getAllCategories()
	{
		return categoryRepository.findAll();
	}
	
	//get list by id
	public Category getCategoryById(Long id)
	{
		Category theCategory = null;
		Optional<Category> optCategory = categoryRepository.findById(id);

		if (optCategory != null) {
			theCategory = optCategory.get();
		}
		return theCategory;
	}
	

}

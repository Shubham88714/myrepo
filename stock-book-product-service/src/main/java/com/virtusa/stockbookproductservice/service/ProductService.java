package com.virtusa.stockbookproductservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.stockbookproductservice.domain.Category;
import com.virtusa.stockbookproductservice.domain.Product;
import com.virtusa.stockbookproductservice.repository.ICategoryRepository;
import com.virtusa.stockbookproductservice.repository.IProductRepository;

@Service
public class ProductService {

	@Autowired
	IProductRepository productRepository;
	
	@Autowired
	ICategoryRepository categoryRespositoty;
	
	//save the product
	public Product saveProduct(Product product)
	{
		Category theCategory = null;
		Product theProduct = null;
		
		Optional<Category> optCategory =  categoryRespositoty.findById(product.getCategory().getId());
		
		if(optCategory!=null)
		{
			theCategory = optCategory.get();
			theCategory.add(product);
			theProduct = productRepository.save(product);
		}
		
		return theProduct;
	}
	
	
}

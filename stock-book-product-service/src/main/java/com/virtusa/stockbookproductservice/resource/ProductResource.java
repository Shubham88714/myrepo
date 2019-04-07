package com.virtusa.stockbookproductservice.resource;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.stockbookproductservice.domain.Product;
import com.virtusa.stockbookproductservice.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductResource {

	@Autowired
	ProductService productService;
	
	@PostMapping("/product")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) throws URISyntaxException {
		Product theProduct  = productService.saveProduct(product);
		
		if(theProduct != null)
			return  ResponseEntity.created(new URI("/api/product/"+theProduct.getId())).build();
		else
			return ResponseEntity.badRequest().build();
	}

}

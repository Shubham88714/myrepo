package com.virtusa.stockbookproductservice.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.stockbookproductservice.domain.Product;
import com.virtusa.stockbookproductservice.domain.Stock;
import com.virtusa.stockbookproductservice.exception.ProductNotAvailableException;
import com.virtusa.stockbookproductservice.repository.IProductRepository;
import com.virtusa.stockbookproductservice.repository.IStockRepository;

@Service
public class StockService {

	@Autowired
	private IStockRepository stockRepository;
	
	@Autowired
	private IProductRepository productRepository;
	
	@Transactional
	public Stock saveStock(Stock stock)
	{	
		Product theProduct = null;
		Stock theStock = null;
		
		Optional<Product> optProduct = productRepository.findById(stock.getProductId());
		
		if(optProduct.isPresent())
		{
			theProduct = optProduct.get();
			theProduct.addStock(stock);
			theStock = stockRepository.save(stock);
		}
		
		return theStock;
	}

	public List<Stock> getStockList() {
		return stockRepository.findAll();
	}

	public List<Stock> getStockListByProductId(Long id) {
	
		List<Stock> stockList = null;
		 stockList = stockRepository.getStockListByProductId(id);
		if(stockList.size()!=0)
			return stockList;
		else 
			throw new ProductNotAvailableException("Product is not available..Check the product id!");
	}
	
	
}

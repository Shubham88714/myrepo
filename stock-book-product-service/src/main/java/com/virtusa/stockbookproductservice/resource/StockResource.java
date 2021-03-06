package com.virtusa.stockbookproductservice.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.virtusa.stockbookproductservice.domain.Stock;
import com.virtusa.stockbookproductservice.service.StockService;

@RestController
@RequestMapping("/api/")
public class StockResource {

	Logger logger = LoggerFactory.getLogger(StockResource.class);
	
	@Autowired
	private StockService stockService;
	
	@PostMapping("/stock")
	public ResponseEntity<Stock> saveStock(@RequestBody Stock stock) throws URISyntaxException
	{
		logger.debug(">>>>entered to post method save stock");
		
		Stock theStock = stockService.saveStock(stock);
	
		return ResponseEntity.created(new URI("/api/stock/"+theStock.getId())).build();
	}
	
	
	@GetMapping("/stock")
	public ResponseEntity<List<Stock>> getStockList()
	{
		List<Stock> stockList = stockService.getStockList();
		return new ResponseEntity<List<Stock>>(stockList,HttpStatus.OK);
	}
	
	@DeleteMapping("/stock/{id}")
	public ResponseEntity<Stock> deleteStock()
	{
		return null;
	}
	
	//working here
	@GetMapping("/stock/product/{id}")
	public ResponseEntity<List<Stock>> getStockByProductId(@PathVariable("id") Long id)
	{
		return ResponseEntity.ok().body(stockService.getStockListByProductId(id));
	}
}

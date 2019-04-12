package com.virtusa.it.stockbookproductservice.category;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.virtusa.it.stockbookproductservice.testdata.InitialTestDataH2;
import com.virtusa.stockbookproductservice.StockBookProductServiceApplication;
import com.virtusa.stockbookproductservice.domain.Category;
import com.virtusa.stockbookproductservice.domain.Product;
import com.virtusa.stockbookproductservice.domain.Stock;
import com.virtusa.stockbookproductservice.exception.CategoryErrorResponse;
import com.virtusa.stockbookproductservice.repository.IProductRepository;
import com.virtusa.stockbookproductservice.repository.IStockRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = { StockBookProductServiceApplication.class })
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class StockIntegrationTest extends InitialTestDataH2 {

	@Autowired
	WebApplicationContext wac;

	@Autowired
	ObjectMapper mapper;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Autowired
	IProductRepository productRepository;

	@Autowired
	IStockRepository stockRepository;

	@Test
	public void saveStockTest() throws Exception {
		Category category = new Category("product category1");
		Product product = new Product("product name1", "product description1", category);

		Product theProduct = productRepository.save(product);
		System.out.println(theProduct);

		Long productId = theProduct.getId();

		Stock stock = new Stock();
		stock.setDate("1196/09/24");
		stock.setQuantity(200L);
		stock.setManufacturer("manufacturer1");
		stock.setCostPrice(10D);
		stock.setSellingPrice(20D);
		stock.setDiscount(2f);
		stock.setTotalCp(200 * 10D);
		stock.setGst(100f);
		stock.setThreshold(50L);
		stock.setProductId(productId);
		System.out.println(stock);

		String postResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/stock").contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(stock)))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse().getHeader("Location");

		System.out.println(postResult);
	}

	@Test
	public void getStockListByProductId() throws Exception {
		Category category = new Category("product category2");
		Product product = new Product("product name2", "product description2", category);

		Product theProduct = productRepository.save(product);
		System.out.println(theProduct);

		Long productId = theProduct.getId();

		Stock stock = new Stock();
		stock.setDate("2010/09/10");
		stock.setQuantity(200L);
		stock.setManufacturer("manufacturer2");
		stock.setCostPrice(10D);
		stock.setSellingPrice(20D);
		stock.setDiscount(2f);
		stock.setTotalCp(200 * 10D);
		stock.setGst(100f);
		stock.setThreshold(50L);
		stock.setProductId(productId);

		Stock theStock = stockRepository.save(stock);

		System.out.println(theStock);

		ResultActions action = mockMvc.perform(MockMvcRequestBuilders.get("/api/stock/product/" + productId))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));

		String response = action.andReturn().getResponse().getContentAsString();
		Stock[] stockList = mapper.readValue(response, Stock[].class);

		assertEquals(stockList[0].getId(), theStock.getId());
		assertEquals(stockList[0].getDate(), theStock.getDate());
		assertEquals(stockList[0].getQuantity(), theStock.getQuantity());
		assertEquals(stockList[0].getManufacturer(), theStock.getManufacturer());
		assertEquals(stockList[0].getCostPrice(), theStock.getCostPrice());
		assertEquals(stockList[0].getSellingPrice(), theStock.getSellingPrice());
		assertEquals(stockList[0].getDiscount(), theStock.getDiscount());
		assertEquals(stockList[0].getTotalCp(), theStock.getTotalCp());
		assertEquals(stockList[0].getGst(), theStock.getGst());
		assertEquals(stockList[0].getThreshold(), theStock.getThreshold());
		assertEquals(stockList[0].getProductId(), theStock.getProductId());

	}

	@Test
	public void getStockListByWrongProductId() throws Exception {
		ResultActions action = mockMvc.perform(MockMvcRequestBuilders.get("/api/stock/product/55884"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));

		String response = action.andReturn().getResponse().getContentAsString();

		CategoryErrorResponse error = mapper.readValue(response, CategoryErrorResponse.class);

		assertEquals(error.getStatus(), 400);
		assertEquals(error.getMessage(), "Product is not available..Check the product id!");

	}

	
	
	// @Test
	public void deleteStock() throws Exception {

		Category category = new Category(103L, "product category3");
		Product product = new Product(103L, "product name3", "product description", category);

		Product theProduct = productRepository.save(product);
		System.out.println(theProduct);

		Long productId = theProduct.getId();

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/stock/1")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	
	
}

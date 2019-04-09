package com.virtusa.it.stockbookproductservice.category;

import static org.hamcrest.core.Is.is;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.virtusa.stockbookproductservice.StockBookProductServiceApplication;
import com.virtusa.stockbookproductservice.domain.Category;
import com.virtusa.stockbookproductservice.domain.Product;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = { StockBookProductServiceApplication.class })
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductIntegrationTest {
	@Autowired
	WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	String response;

	@Test
	public void A_saveProduct() throws Exception {
		Category category = new Category(1L, "electronics");

		String categoryResponse = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/category").contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(category)))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse().getHeader("Location");

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>> " + categoryResponse);

		Product product = new Product("Ext-Board", "120m wire extension", category);
		response = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/product").contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(product)))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse().getHeader("Location");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>> " + response);
	}

	
	public void B_getProductById() throws Exception {

		Category category = new Category(1L, "electronics");

		String categoryResponse = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/category").contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(category)))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse().getHeader("Location");

		System.out.println(" B: >>>>>>>>>>>>>>>>>>>>>>>>>>>> " + categoryResponse);

		Product product = new Product("PWR-BK", "1000mah", category);
		response = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/product").contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(product)))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse().getHeader("Location");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>> " + response);
		System.out.println(response);
		
		mockMvc.perform(MockMvcRequestBuilders.get(response)).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id", is(2)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.name", is("PWR-BK")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.description", is("1000mah")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.category.id", is("1")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.category.name", is("electronics")));
	}
}

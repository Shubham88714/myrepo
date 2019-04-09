package com.virtusa.stockbookproductservice.category;

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

import com.google.gson.Gson;
import com.virtusa.stockbookproductservice.domain.Category;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class CategoryTest {

	
	@Autowired
	WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void saveCategory() throws Exception
	{
		Category category = new Category("stationary");
		
		  ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/category")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(category)))
				.andExpect(MockMvcResultMatchers.status().isCreated());
		  System.out.println("successfully executed");
		  System.out.println(result.andReturn().getResponse().getContentAsString());
	}
	
	//get list of categories
	@Test
	public void getListOfCategory() throws Exception
	{
		Category cat1 = new Category("electronics");
		Category cat2 = new Category("grocery");
		
		/*
		 * ResultActions getResult =
		 * mockMvc.perform(MockMvcRequestBuilders.get("/api/categories"))
		 * .andExpect(MockMvcResultMatchers.status().isOk())
		 * .andExpect(MockMvcResultMatchers.content().contentType(MediaType.
		 * APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(),
		 * Charset.forName("utf8")) .andExpect(MockMvcResultMatchers.jsonPath("$",
		 * matcher));
		 */
		
	}
}

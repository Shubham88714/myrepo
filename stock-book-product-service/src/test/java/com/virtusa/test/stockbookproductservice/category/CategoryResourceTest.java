package com.virtusa.test.stockbookproductservice.category;

import static org.mockito.ArgumentMatchers.anyLong;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;

import com.virtusa.stockbookproductservice.domain.Category;
import com.virtusa.stockbookproductservice.resource.CategoryResource;
import com.virtusa.stockbookproductservice.service.CategoryService;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
public class CategoryResourceTest {

	@Mock
	CategoryService categoryService;

	@InjectMocks
	CategoryResource categoryResource;

	// delete category by id
	@Test
	public void deleteCategoryByid() throws Exception {

		Category category = new Category(101L, "furniture");

		Mockito.when(categoryService.deleteCategory(anyLong())).thenReturn(category);

		categoryResource.deleteCategory(101L);

	}
}

package com.onpassive.odesk.controller.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onpassive.odesk.controller.ProductController;
import com.onpassive.odesk.model.ProductModel;
import com.onpassive.odesk.service.ProductsService;
import com.onpassive.odesk.service.impl.CategoriesServiceImpl;
import com.onpassive.odesk.service.impl.CeoServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { ProductController.class })
@AutoConfigureMockMvc
@ContextConfiguration(classes = ProductController.class)
public class ProductControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	ProductsService productsService;
	
	@MockBean
    CeoServiceImpl ceoServiceImpl;
	
	@MockBean
    CategoriesServiceImpl categoriesServiceImpl;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Autowired
	ObjectMapper objectMapper;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getAllProducts() throws Exception {
		ProductModel pm = new ProductModel();
		pm.set_id("Demo ID");
		
		// Preparing Return Type
		List<ProductModel> allProducts = new ArrayList<ProductModel>();
		allProducts.add(pm);
		
		// Mocking Behavior
		when(productsService.getAllProducts()).thenReturn(allProducts);
		
		// Moke Request
		var mockRequest = MockMvcRequestBuilders.get("/api/proddatta");
		var response = mockMvc.perform(mockRequest).andReturn().getResponse();
		
		String string = response.getContentAsString();
		System.out.println(string);

		assertEquals(200, response.getStatus());
	}

}

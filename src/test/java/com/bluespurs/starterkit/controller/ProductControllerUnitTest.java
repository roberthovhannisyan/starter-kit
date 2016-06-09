package com.bluespurs.starterkit.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bluespurs.starterkit.UnitTest;
import com.bluespurs.starterkit.resource.Product;
import com.bluespurs.starterkit.service.ProductSearchService;

@Category(UnitTest.class)
public class ProductControllerUnitTest extends UnitTest {
	private MockMvc mockMvc;
	
	@Mock
	private ProductSearchService searchService;
	
    @Before
    public void setUp() {
        super.setUp();
        mockMvc = MockMvcBuilders.standaloneSetup(new ProductController(searchService)).build();
    }

    @Test
    public void testNameParameterNull() throws Exception {
    	mockMvc.perform(get("/product/search"))
            .andExpect(status().isBadRequest());
    }
    
    @Test
    public void testNameParameterEmpty() throws Exception {
    	mockMvc.perform(get("/product/search?name"))
            .andExpect(status().isBadRequest());
    }
        
    @Test
    public void testReturnedProductNonNull() throws Exception {
    	Product result = new Product("iPad Mini", new BigDecimal("155.50"), "Walmart");
    	Mockito.when(searchService.findCheapestProduct("iPad"))
    		.thenReturn(result);
        
    	mockMvc.perform(get("/product/search?name=iPad"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.productName").value(result.getProductName()))
            .andExpect(jsonPath("$.bestPrice").value(result.getBestPrice().doubleValue()))
            .andExpect(jsonPath("$.location").value(result.getLocation()));
    }

    @Test
    public void testReturnedProductIsNull() throws Exception {
    	Mockito.when(searchService.findCheapestProduct(Mockito.anyString()))
    		.thenReturn(null);
        
    	mockMvc.perform(get("/product/search?name=iPad"))
            .andExpect(status().isOk())
            .andExpect(content().string(""));
    }

}

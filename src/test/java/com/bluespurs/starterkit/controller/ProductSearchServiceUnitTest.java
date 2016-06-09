package com.bluespurs.starterkit.controller;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;

import org.mockito.Mock;
import org.mockito.Mockito;

import com.bluespurs.starterkit.UnitTest;
import com.bluespurs.starterkit.repository.ProductSearchRepository;
import com.bluespurs.starterkit.resource.Product;
import com.bluespurs.starterkit.service.ProductSearchService;
import com.bluespurs.starterkit.service.ProductSearchServiceImpl;

@Category(UnitTest.class)
public class ProductSearchServiceUnitTest extends UnitTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Mock
	private ProductSearchRepository repositoryWalmart;

	@Mock
	private ProductSearchRepository repositoryBestBuy;
	
	private ProductSearchService searchService;
	
	@Before
    public void setUp() {
        super.setUp();
        this.searchService = new ProductSearchServiceImpl(
        		new ProductSearchRepository[]{repositoryWalmart, repositoryBestBuy});
    }

	@Test
	public void throwExceptionWhenNameIsNull() throws Exception {
		this.thrown.expect(IllegalArgumentException.class);
		this.searchService.findCheapestProduct(null);
	}

	@Test
	public void throwExceptionWhenNameIsEmpty() throws Exception {
		this.thrown.expect(IllegalArgumentException.class);
		this.searchService.findCheapestProduct("");
	}
	
	@Test
	public void findCheapestProductMustReturnProduct1() {
		
		Product resultWalmart = new Product("iPad Mini @Walmart", new BigDecimal("155.50"), "Walmart");
		Product resultBestBuy = new Product("iPad Mini @BestBuy", new BigDecimal("168.20"), "BestBuy");
		
		Mockito.when(repositoryWalmart.findCheapestProduct("iPad")).thenReturn(resultWalmart);
		Mockito.when(repositoryBestBuy.findCheapestProduct("iPad")).thenReturn(resultBestBuy);
		
		Product actual = this.searchService.findCheapestProduct("iPad");
		Assert.assertEquals(resultWalmart, actual);
	}
		
	@Test
	public void findCheapestProductMustReturnProduct2() {
		
		Product resultBestBuy = new Product("iPad Mini @BestBuy", new BigDecimal("168.20"), "BestBuy");
		
		Mockito.when(repositoryWalmart.findCheapestProduct(Mockito.anyString())).thenReturn(null);
		Mockito.when(repositoryBestBuy.findCheapestProduct("iPad")).thenReturn(resultBestBuy);
		
		Product actual = this.searchService.findCheapestProduct("iPad");
		Assert.assertEquals(resultBestBuy, actual);
	}
	
	@Test
	public void findCheapestProductMustReturnNull() {
		
		Mockito.when(repositoryWalmart.findCheapestProduct(Mockito.anyString())).thenReturn(null);
		Mockito.when(repositoryBestBuy.findCheapestProduct(Mockito.anyString())).thenReturn(null);
		
		Product actual = this.searchService.findCheapestProduct("iPad");
		Assert.assertNull(actual);
	}
	
	
	
}

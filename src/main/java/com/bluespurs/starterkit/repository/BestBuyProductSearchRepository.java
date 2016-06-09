package com.bluespurs.starterkit.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.bluespurs.starterkit.resource.Product;

/**
 * Represents the products searching repository implementation for the Best Buy API.
 */
@Repository
public class BestBuyProductSearchRepository implements ProductSearchRepository{

	public static final String BESTBUY_LOCATION = "BestBuy";
	
	@Value("${bestbuy.api-baseurl}")
	private String apiBaseUrl;
	@Value("${bestbuy.api-key}")
	private String apiKey;
	
	
	/**
	 * Finds and returns the cheapest product in the Best Buy repository.
	 * @param name: The name of the product to search. Method should also match the 
	 * 				products that contains the specified name.
	 */
	@Override
	public Product findCheapestProduct(String name) {
		
		// Build the url to match the products with provided name and return 
		// them sorted by sale price in ascending order.
		String url = apiBaseUrl + "/products(name={name}*)?apiKey={apiKey}&format={format}&show={show}&sort={sort}&pageSize={pageSize}";
		
		// Create the parameters to substitute in the url.
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", name);
		params.put("apiKey", apiKey);
		params.put("format", "json");
		params.put("show", "productId,name,salePrice");
		params.put("sort", "salePrice");
		params.put("pageSize", "1");
		
		RestTemplate template = new RestTemplate();
		BestBuySearchResponse response = template.getForObject(url, BestBuySearchResponse.class, params);
		if (response.getTotal() > 0) {
			// First product is the cheapest one.
			BestBuySearchResponseProduct[] responseProducts = response.getProducts();
			return new Product(
					responseProducts[0].getName(),
					responseProducts[0].getSalePrice(),
					BESTBUY_LOCATION);
		}
		return null;
	}

}

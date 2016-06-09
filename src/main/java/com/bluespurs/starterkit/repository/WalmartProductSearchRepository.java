package com.bluespurs.starterkit.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.bluespurs.starterkit.resource.Product;

/**
 * Represents the products searching repository implementation for the Walmart API.
 */
@Repository
public class WalmartProductSearchRepository implements ProductSearchRepository {

	public static final String WALMART_LOCATION = "Walmart";
	
	@Value("${walmart.api-baseurl}")
	private String apiBaseUrl;
	@Value("${walmart.api-key}")
	private String apiKey;
	
	/**
	 * Finds and returns the cheapest product in the Walmart repository.
	 * @param name: The name of the product to search. Method should also match the 
	 * 				products that contains the specified name.
	 */
	@Override
	public Product findCheapestProduct(String name) {
		
		// Build the url to match the products with provided name and return 
		// them sorted by sale price in ascending order.
		String url = apiBaseUrl + "/search?apiKey={apiKey}&query={name}&format={format}&sort={sort}&ord={ord}&numItems={numItems}";
		
		// Create the parameters to replace in the url.
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", name);
		params.put("apiKey", apiKey);
		params.put("format", "json");
		params.put("sort", "price");
		params.put("ord", "asc");
		params.put("numItems", "1");
		
		RestTemplate template = new RestTemplate();
		WalmartSearchResponse response = template.getForObject(url, WalmartSearchResponse.class, params);
		if (response.getTotalResults() > 0) {
			WalmartSearchResponseItem[] responseProducts = response.getItems();
			return new Product(
					responseProducts[0].getName(),
					responseProducts[0].getSalePrice(),
					WALMART_LOCATION);
		}
		return null;
	}

}
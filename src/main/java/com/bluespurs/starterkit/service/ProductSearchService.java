package com.bluespurs.starterkit.service;

import com.bluespurs.starterkit.resource.Product;

/**
 * Represents the products searching service.
 */
public interface ProductSearchService {
	
	/**
	 * Finds and returns the product with the cheapest price using specified name.
	 * @param name: The name of the product to search. Method will also match the 
	 * 				products that contains the specified name.
	 * @return The product with the cheapest price if found; otherwise null.
	 */
	public Product findCheapestProduct(String name);
}

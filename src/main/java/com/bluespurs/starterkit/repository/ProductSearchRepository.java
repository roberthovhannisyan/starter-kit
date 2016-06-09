package com.bluespurs.starterkit.repository;

import com.bluespurs.starterkit.resource.Product;

/**
 * Represents the products searching repository.
 */
public interface ProductSearchRepository {
	
	/**
	 * Finds and returns the cheapest product in the repository.
	 * @param name: The name of the product to search. Method should also match the 
	 * 				products that contains the specified name.
	 */
	public Product findCheapestProduct(String name);
}

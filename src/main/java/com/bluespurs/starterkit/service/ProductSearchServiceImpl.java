package com.bluespurs.starterkit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluespurs.starterkit.repository.ProductSearchRepository;
import com.bluespurs.starterkit.resource.Product;

/**
 * Represents the products searching service implementation.
 * The service performs the search in all repositories implementing 
 * ProductSearchRepository interface.
 */
@Service
public class ProductSearchServiceImpl implements ProductSearchService {

	private final ProductSearchRepository[] repositories;
	
	/**
     * Constructs the product search service implementation using provided service.
     * @param repositories: The product search repositories to be used.
     */
	@Autowired
	public ProductSearchServiceImpl(ProductSearchRepository[] repositories) {
		this.repositories = repositories;
	}
	
	/**
	 * Finds and returns the product with the cheapest price using specified name.
	 * @param name: The name of the product to search. Method will also match the 
	 * 				products that contains the specified name.
	 * @return The product with the cheapest price if found; otherwise null.
	 */
	@Override
	public Product findCheapestProduct(String name) {
		if (name == null || name.isEmpty()) {
    		throw new IllegalArgumentException("The 'name' parameter must not be null or empty");
        }
		Product result = null;
		for(ProductSearchRepository repository: repositories) {
			// find the cheapest product in the repository.
			Product nextProduct = repository.findCheapestProduct(name);
			// Check if it is cheaper than products found in previous repositories.
			if (nextProduct != null) {
				if(result == null || result.getBestPrice().compareTo(nextProduct.getBestPrice()) > 0) {
					result = nextProduct;
				}
			}
		}
		return result;
	}

}

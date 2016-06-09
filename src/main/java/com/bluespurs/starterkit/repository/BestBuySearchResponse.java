package com.bluespurs.starterkit.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents the response received from the Best Buy API
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BestBuySearchResponse {
	
	private long total;
	private BestBuySearchResponseProduct[] products;

	/**
	 * Gets the total number of matched products.
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * Gets the array of matched products.
	 */
	public BestBuySearchResponseProduct[] getProducts() {
		return products;
	}

}

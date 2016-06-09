package com.bluespurs.starterkit.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents the response received from the Walmart API
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WalmartSearchResponse {
	
	private long totalResults;
	private WalmartSearchResponseItem[] items;

	/**
	 * Gets the total number of matched products.
	 */
	public long getTotalResults() {
		return totalResults;
	}

	/**
	 * Gets the array of matched products.
	 */
	public WalmartSearchResponseItem[] getItems() {
		return items;
	}

}


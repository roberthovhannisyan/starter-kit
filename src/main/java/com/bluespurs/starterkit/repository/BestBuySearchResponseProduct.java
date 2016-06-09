package com.bluespurs.starterkit.repository;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents the information about the matched product.
 * Contains only those attributes that are of interest for the API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BestBuySearchResponseProduct {

	private long productId;
	private String name;
	private BigDecimal salePrice;

	/**
	 * Gets the unique identifier of the product.
	 */
	public long getProductId() {
		return productId;
	}
	
	/**
	 * Gets the complete name of the product.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the sale price of the product.
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	
}

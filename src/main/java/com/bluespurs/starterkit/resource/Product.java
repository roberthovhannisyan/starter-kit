package com.bluespurs.starterkit.resource;

import java.math.BigDecimal;

/**
 * Represents the product information.
 */
public class Product {

	private String productName;
	private BigDecimal bestPrice;
	private String currency;
	private String location;
	
	/**
	 * Constructs the product with the provided location, name and best price.
	 * @param productName: The name of the product
	 * @param bestPrice: The best price found for the product.
	 * @param location: The location of the card, e.g. Walmart, BestBuy
	 */
	public Product(String productName, BigDecimal bestPrice, String location) {
		this.productName = productName;
		this.bestPrice = bestPrice;
		this.currency = "CAD";
		this.location = location;
	}

	/**
	 * Gets the complete name of the product.
	 */
	public String getProductName() {
		return productName;
	}
	
	/**
	 * Gets the best price found for the product.
	 */
	public BigDecimal getBestPrice() {
		return bestPrice;
	}

	/**
	 * Gets the currency for the price. 
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Gets the location of the found product, e.g. Walmart, BestBuy.
	 */
	public String getLocation() {
		return location;
	}

}

package com.bluespurs.starterkit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluespurs.starterkit.resource.Product;
import com.bluespurs.starterkit.service.ProductSearchService;

/**
 * Controller class for the product queries.
 */
@Controller
@RequestMapping("/product")
public class ProductController {
	
    private final ProductSearchService service;
    
    /**
     * Constructs the product controller using provided service.
     * @param service: The product search service.
     */
    @Autowired
    public ProductController(ProductSearchService service) {
    	this.service = service;
    }
    
    /**
     * The search page returns the cheapest product for the provided name.
     * The method is mapped to "/product/search" as a GET request.
     */
    @RequestMapping("/search")
    public @ResponseBody Product searchProduct(@RequestParam String name) {
    	if (name == null || name.isEmpty()) {
    		throw new IllegalArgumentException("The 'name' parameter must not be null or empty");
        }
    	return service.findCheapestProduct(name); 
    }
    
}

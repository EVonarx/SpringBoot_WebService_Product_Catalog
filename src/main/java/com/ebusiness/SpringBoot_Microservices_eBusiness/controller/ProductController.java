package com.ebusiness.SpringBoot_Microservices_eBusiness.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ebusiness.SpringBoot_Microservices_eBusiness.dao.ProductDao;
import com.ebusiness.SpringBoot_Microservices_eBusiness.model.Product;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@CrossOrigin
@RestController
public class ProductController {

	@Autowired
	private ProductDao productDao;

		// GET help
		@GetMapping(value = "/help")
		public String help() {
			return "<h1 style=\"color:green\">How to use this web service?</h1><br/>"
					+ "<h2> Get method => /products => list of the products</h2><br/>"
					+ "<h2> Get method => /products/1 => display the product with id=1</h2><br/>" 
					+ "<h2> Post method => /products => add a product to the DB => use Postman for example</h2><br/>"
					+ "<h2> Put method => /products => modify a product in the DB (based on the id) => use Postman for example</h2><br/>"
					+ "<h2> Delete method => /products/1 => delete the product with id=1 => use Postman for example</h2><br/>" ;
		}
	
	// GET products
	@GetMapping(value = "/products")
	public MappingJacksonValue productList() {

		Iterable<Product> products = productDao.findAll();
		return myProductsFilters(products);
	}

	// GET products/{id}
	@GetMapping(value = "/products/{id}")
	public MappingJacksonValue displayProduct(@PathVariable int id) {
		Product product = productDao.findById(id);
		return myProductsFilters(product);
	}

	// POST products
	@PostMapping(value = "/products")
	public ResponseEntity<Void> addProduct(@RequestBody Product p) { // productDao.save(p);

		Product productAdded = productDao.save(p);

		if (productAdded == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(productAdded.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
/*
	@GetMapping(value = "test/products/{priceLimit}")
	public MappingJacksonValue findHighPricesProducts(@PathVariable int priceLimit) {

		Iterable<Product> products = productDao.findByPriceGreaterThan(400);
		return myProductsFilters(products);

	}
*/
	// GET test/products/{search}
	@GetMapping(value = "test/products/{search}")
    public MappingJacksonValue findProductsByName(@PathVariable String search) {
		Iterable<Product> products = productDao.findByNameLike("%"+search+"%");
		return myProductsFilters(products);
    }
	
	// DELETE /products/{id}
	@DeleteMapping (value = "/products/{id}")
	   public void deleteProduct(@PathVariable int id) {

	       productDao.deleteById(id);
	   }
	
	// PUT /products
	@PutMapping (value = "/products")
	  public void updateProduit(@RequestBody Product product) {

	      productDao.save(product);
	  }

	
	public MappingJacksonValue myProductsFilters(Object obj) {

		SimpleBeanPropertyFilter myFilter = SimpleBeanPropertyFilter.serializeAllExcept("buyingPrice");

		FilterProvider FilterList = new SimpleFilterProvider().addFilter("myDynamicFilter", myFilter);
		MappingJacksonValue filterProducts = null;
		if (obj instanceof Product)
			filterProducts = new MappingJacksonValue((Product) obj);
		else
			filterProducts = new MappingJacksonValue((Iterable<Product>) obj);
		filterProducts.setFilters(FilterList);

		return filterProducts;
	}

}

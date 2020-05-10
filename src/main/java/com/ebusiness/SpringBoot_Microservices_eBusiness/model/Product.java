package com.ebusiness.SpringBoot_Microservices_eBusiness.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
//@JsonFilter("myDynamicFilter")
public class Product {
    
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	private int price;
	private int bprice;


	
	public Product() {
		super();
	}

	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

	
	
	public int getBprice() {
		return bprice;
	}


	public void setBprice(int bprice) {
		this.bprice = bprice;
	}


	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", buyingPrice=" + bprice + "]";
	}

}

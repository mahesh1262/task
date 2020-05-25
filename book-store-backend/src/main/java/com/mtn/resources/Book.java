package com.mtn.resources;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "title", nullable = false)
	private String title;
	@Column(name = "year", nullable = false)
	private Integer year;
	@Column(name = "price", nullable = false)
	private double price;
	@Column(name = "language", nullable = false)
	private String language;
	@Column(name = "catagory", nullable = false)
	private String catagory;
	
	
	
	public Book(){
		
	}
	public Book(String title, Integer year, double price, String language, String catagory) {
		
		this.title = title;
		this.year = year;
		this.price = price;
		this.language = language;
		this.catagory = catagory;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}


	public String getCatagory() {
		return catagory;
	}


	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}
	
	
	
	
	
	
}

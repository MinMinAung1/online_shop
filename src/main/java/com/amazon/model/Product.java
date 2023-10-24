package com.amazon.model;

public class Product {

	private Long id;
	private String name;
	private Double price;
	private String description;
	private Long userid;
	private String image;
	private Double rating;
	
	public Product() {}

	public Product(Long id, String name, Double price, String description, Long userid, String image, Double rating) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.userid = userid;
		this.image = image;
		this.rating = rating;
	}

	public Product(String name, Double price, String description, Long userid, String image, Double rating) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.userid = userid;
		this.image = image;
		this.rating = rating;
	}

	public Product(String name, Double price, String description, String image, Double rating) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.image = image;
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", userid=" + userid + ", image=" + image + ", rating=" + rating + "]";
	}

}

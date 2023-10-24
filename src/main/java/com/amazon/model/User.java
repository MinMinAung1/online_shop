package com.amazon.model;

public class User {

	private Long id;
	private String name;
	private String email;
	private String password;
	private String address;
	private String role;
	private Boolean locked;

	public User(Long id, String name, String email, String password, String address, String role, Boolean locked) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.role = role;
		this.locked = locked;
	}

	public User(String name, String email, String password, String address, String role, Boolean locked) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.role = role;
		this.locked = locked;
	}

	public User(String name, String email, String password, String address, String role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.role = role;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", address="
				+ address + ", role=" + role + ", locked=" + locked + "]";
	}

}

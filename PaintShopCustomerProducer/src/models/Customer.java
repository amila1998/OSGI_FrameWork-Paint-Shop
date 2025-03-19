package models;

import java.io.Serializable;

public class Customer implements Serializable{
	private String name;
	private String address;
	private String email;
	private String phone;
	private String id;
	
	
	public Customer() {}


	public Customer(String name, String address, String email, String phone, String id) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	
	

	
	
}

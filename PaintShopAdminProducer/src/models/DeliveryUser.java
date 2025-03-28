package models;

import java.io.Serializable;

public class DeliveryUser implements Serializable {

	private String name;
	private String phone;
	private String username;
	private String password;

	public DeliveryUser() {
	}

	public DeliveryUser(String name, String phone, String username, String password) {
		super();
		this.name = name;
		this.phone = phone;
		this.username = username;
		this.password = password;
	}
	
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {

		return "Delivery User name:" + this.name + "\n Delivery User contact number" + this.phone;
	}
}

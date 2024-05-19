package com.adminx.UserDataStore.model;


public class User {
	private String id;
	private String name;
	private String address;
	private String nationality;
	private String nic;
	
	
	public User(String name, String address, String nationality, String nic) {
		super();
		this.name = name;
		this.address = address;
		this.nationality = nationality;
		this.nic = nic;
	}
	
	
	public User(String id, String name, String address, String nationality, String nic) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.nationality = nationality;
		this.nic = nic;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getNic() {
		return nic;
	}
	public void setNic(String nic) {
		this.nic = nic;
	}
	
}

package com.niit.discussionB.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class User extends BaseDomain
{
	@Id
	private String id;
	private String name;
	private String password;
	private String address;
	private boolean status;
	private boolean isOnline;
	
	
	public User() {
		super();
	}
	public User(String id, String name, String password, String address, boolean status, boolean isOnline) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.address = address;
		this.status = status;
		this.isOnline = isOnline;
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
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean isOnline() {
		return isOnline;
	}
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	

}

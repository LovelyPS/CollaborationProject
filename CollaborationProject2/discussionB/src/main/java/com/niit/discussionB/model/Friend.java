package com.niit.discussionB.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Friend extends BaseDomain 
{
	@Id
	int id;
	int userId;
	int friendId;
	int status;
	boolean isOnline;
	
	
	public Friend() {
		super();
	}
	public Friend(int id, int userId, int friendId, int status, boolean isOnline) {
		super();
		this.id = id;
		this.userId = userId;
		this.friendId = friendId;
		this.status = status;
		this.isOnline = isOnline;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public boolean isOnline() {
		return isOnline;
	}
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	
	
	

}

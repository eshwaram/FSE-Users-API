package com.projectmanager.microservices.usersservice.entities;

import java.util.ArrayList;
import java.util.List;

public class Users {
	private List<UserData> users;
	private String port;

	public List<UserData> getUsers() {
		return users;
	}

	public void setUsers(List<UserData> users) {
		this.users = users;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
	public void addUsers(UserData user) {
		if(null == users) {
			users = new ArrayList<UserData>();
		}
		users.add(user);
	}
}

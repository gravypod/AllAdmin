package com.gravypod.AllAdmin.user;


public interface IUser {
	
	public boolean hasPermission(String node);
	
	public void sendCommandFaliure(String command);
	
}
